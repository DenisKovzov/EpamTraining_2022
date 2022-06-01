package vsu.by.services.implementation;

import vsu.by.dao.DaoException;
import vsu.by.dao.EmployeeDao;
import vsu.by.dao.UserDao;
import vsu.by.models.User;
import vsu.by.services.exceptions.ServiceException;
import vsu.by.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса UserService
 *
 * @author Kovzov Denis
 * @see vsu.by.services.UserService
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            User tempUser = userDao.readByLogin(user.getLogin());

            if (tempUser != null && tempUser.getId() != user.getId()) {
                throw new ServiceException("This login already exists");
            }

            if (user.getId() != null) {
                userDao.update(user);

            } else {
                userDao.create(user);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for (Long id : ids) {
                userDao.delete(id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllIsNotEmployee() throws ServiceException {
        try {
            List<User> users = userDao.readAll();

            return users.stream().filter(e -> !employeeDao.isExist(e.getId())).collect(Collectors.toList());

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
