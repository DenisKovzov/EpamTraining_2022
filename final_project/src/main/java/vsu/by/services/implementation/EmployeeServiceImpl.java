package vsu.by.services.implementation;

import vsu.by.dao.DaoException;
import vsu.by.dao.EmployeeDao;
import vsu.by.dao.UserDao;
import vsu.by.models.Employee;
import vsu.by.models.User;
import vsu.by.services.EmployeeService;
import vsu.by.services.exceptions.ServiceException;
import vsu.by.services.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса EmployeeService
 *
 * @author Kovzov Denis
 * @see vsu.by.services.EmployeeService
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;
    private UserDao userDao;

    private Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Employee findById(Long id) throws ServiceException {
        try {
            Employee employee = employeeDao.read(id);

            setData(employee);

            return employee;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<Employee> findAllByIds(List<Long> ids) throws ServiceException {
        List<Employee> employees = findAll();
        return employees.stream().filter(e -> ids.contains(e.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findAllFree() throws ServiceException {
        List<Employee> employees = findAll();
        List<Employee> result = employees.stream().filter(e -> e.getBrigadeId() == null).collect(Collectors.toList());
        return result;
    }


    @Override
    public List<Employee> findAll() throws ServiceException {
        try {
            List<Employee> employees = new ArrayList<>(employeeDao.readAll());

            for (Employee employee : employees) {
                setData(employee);
            }
            return employees;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Employee employee) throws ServiceException {
        try {
            if (employee.getId() == null) {
                throw new ServiceException("User is not found");
            }

            if (employeeDao.isExist(employee.getId())) {
                employeeDao.update(employee);
            } else {
                employeeDao.create(employee);
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for (Long id : ids) {
                employeeDao.delete(id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void setData(Employee employee) {
        User user;
        try {
            user = userDao.read(employee.getId());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        employee.setLogin(user.getLogin());
        employee.setPassword(user.getPassword());
        employee.setRole(user.getRole());
    }
}
