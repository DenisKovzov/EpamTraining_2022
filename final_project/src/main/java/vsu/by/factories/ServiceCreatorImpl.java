package vsu.by.factories;

import vsu.by.dao.BrigadeDao;
import vsu.by.dao.EmployeeDao;
import vsu.by.dao.FlightDao;
import vsu.by.dao.UserDao;
import vsu.by.dao.mysql.BrigadeDaoImpl;
import vsu.by.dao.mysql.EmployeeDaoImpl;
import vsu.by.dao.mysql.FlightDaoImpl;
import vsu.by.dao.mysql.UserDaoImpl;
import vsu.by.pool.ConnectionPool;
import vsu.by.pool.ConnectionPoolException;
import vsu.by.services.*;
import vsu.by.services.implementation.*;

import java.sql.Connection;

/**
 * реализация интерфейса
 *
 * @author Kovzov Denis
 */
public class ServiceCreatorImpl implements ServiceCreator {

    private Connection connection;

    private Transaction transaction;

    private UserService userService;
    private EmployeeService employeeService;
    private BrigadeService brigadeService;

    private FlightService flightService;


    private UserDao userDao;
    private EmployeeDao employeeDao;
    private BrigadeDao brigadeDao;

    private FlightDao flightDao;

    @Override
    public UserService getUserService() throws ServiceCreatorException {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setUserDao(getUserDao());
            userServiceImpl.setEmployeeDao(getEmployeeDao());
            userService = userServiceImpl;
        }
        return userService;
    }

    @Override
    public EmployeeService getEmployeeService() throws ServiceCreatorException {
        if (employeeService == null) {
            EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
            employeeServiceImpl.setEmployeeDao(getEmployeeDao());
            employeeServiceImpl.setUserDao(getUserDao());
            employeeServiceImpl.setTransaction(getTransaction());
            employeeService = employeeServiceImpl;
        }
        return employeeService;
    }

    @Override
    public BrigadeService getBrigadeService() throws ServiceCreatorException {
        if (brigadeService == null) {
            BrigadeServiceImpl brigadeServiceImpl = new BrigadeServiceImpl();
            brigadeServiceImpl.setFlightDao(getFlightDao());
            brigadeServiceImpl.setEmployeeDao(getEmployeeDao());
            brigadeServiceImpl.setBrigadeDao(getBrigadeDao());
            brigadeServiceImpl.setUserDao(getUserDao());
            brigadeServiceImpl.setTransaction(getTransaction());
            brigadeService = brigadeServiceImpl;
        }
        return brigadeService;
    }

    @Override
    public FlightService getFlightService() throws ServiceCreatorException {
        if (flightService == null) {
            FlightServiceImpl flightServiceImpl = new FlightServiceImpl();
            flightServiceImpl.setFlightDao(getFlightDao());
            flightServiceImpl.setBrigadeDao(getBrigadeDao());
            flightService = flightServiceImpl;
        }
        return flightService;
    }

    @Override
    public UserDao getUserDao() throws ServiceCreatorException {
        if (userDao == null) {
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            userDaoImpl.setConnection(getConnection());
            userDao = userDaoImpl;
        }
        return userDao;
    }

    @Override
    public EmployeeDao getEmployeeDao() throws ServiceCreatorException {
        if (employeeDao == null) {
            EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
            employeeDaoImpl.setConnection(getConnection());
            employeeDao = employeeDaoImpl;
        }
        return employeeDao;
    }

    @Override
    public BrigadeDao getBrigadeDao() throws ServiceCreatorException {
        if (brigadeDao == null) {
            BrigadeDaoImpl brigadeDaoImpl = new BrigadeDaoImpl();
            brigadeDaoImpl.setConnection(getConnection());
            brigadeDao = brigadeDaoImpl;
        }
        return brigadeDao;
    }

    @Override
    public FlightDao getFlightDao() throws ServiceCreatorException {
        if (flightDao == null) {
            FlightDaoImpl flightDaoImpl = new FlightDaoImpl();
            flightDaoImpl.setConnection(getConnection());
            flightDao = flightDaoImpl;
        }
        return flightDao;
    }

    @Override
    public Transaction getTransaction() throws ServiceCreatorException {
        if (transaction == null) {
            TransactionImpl transactionImpl = new TransactionImpl();
            transactionImpl.setConnection(getConnection());
            transaction = transactionImpl;
        }
        return transaction;
    }

    @Override
    public Connection getConnection() throws ServiceCreatorException {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (ConnectionPoolException e) {
                throw new ServiceCreatorException(e);
            }
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
        }
    }
}
