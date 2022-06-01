package vsu.by.services.implementation;

import vsu.by.dao.*;
import vsu.by.models.Brigade;
import vsu.by.models.Employee;
import vsu.by.models.User;
import vsu.by.services.BrigadeService;
import vsu.by.services.exceptions.ServiceException;
import vsu.by.services.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса BrigadeService
 *
 * @author Kovzov Denis
 * @see vsu.by.services.BrigadeService
 */
public class BrigadeServiceImpl implements BrigadeService {
    private BrigadeDao brigadeDao;
    private EmployeeDao employeeDao;
    private UserDao userDao;

    private FlightDao flightDao;

    private Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setFlightDao(FlightDao flightDao) {
        this.flightDao = flightDao;
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setBrigadeDao(BrigadeDao brigadeDao) {
        this.brigadeDao = brigadeDao;
    }


    @Override
    public Brigade findById(Long id) throws ServiceException {
        try {
            Brigade brigade = brigadeDao.read(id);

            brigade.setEmployees(getEmployeesByBrigadeId(id));

            return brigade;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Brigade> findAll() throws ServiceException {
        try {
            List<Brigade> brigades = new ArrayList<>(brigadeDao.readAll());

            for (Brigade brigade : brigades) {
                brigade.setEmployees(getEmployeesByBrigadeId(brigade.getId()));
            }
            return brigades;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Brigade brigade) throws ServiceException {
        try {
            transaction.start();
            if (brigade.getId() != null) {

                List<Employee> employeesPrev = employeeDao.readAllByBrigadeId(brigade.getId());

                employeesPrev.stream().forEach(e -> e.setBrigadeId(null));

                saveEmployees(employeesPrev);

                brigade.getEmployees().stream().forEach(e -> e.setBrigadeId(brigade.getId()));
                saveEmployees(brigade.getEmployees());
                brigadeDao.update(brigade);
            } else {

                if (brigadeDao.isNameExist(brigade.getName())) {
                    throw new ServiceException("This name already exists");
                }


                Long brigadeId = brigadeDao.create(brigade);
                brigade.getEmployees().stream().forEach(e -> e.setBrigadeId(brigadeId));
                saveEmployees(brigade.getEmployees());
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                transaction.rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            transaction.start();
            if (flightDao.haveBrigade(id)) {
                throw new ServiceException("The brigade uses in flight");
            }

            List<Employee> employees = findById(id).getEmployees();

            employees.stream().forEach(e -> e.setBrigadeId(null));
            saveEmployees(employees);

            brigadeDao.delete(id);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                transaction.rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    private List<Employee> getEmployeesByBrigadeId(Long brigadeId) {
        try {
            List<Employee> employees = employeeDao.readAllByBrigadeId(brigadeId);

            for (Employee employee : employees) {
                User user = userDao.read(employee.getId());

                employee.setLogin(user.getLogin());
                employee.setPassword(user.getPassword());
                employee.setRole(user.getRole());
            }

            return employees;
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            try {
                employeeDao.update(employee);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
