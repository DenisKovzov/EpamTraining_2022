package vsu.by.factories;

import vsu.by.dao.*;
import vsu.by.services.*;

import java.sql.Connection;

/**
 * Интерфейс для получание нужных объектов
 *
 * @author Kovzov Denis
 */
public interface ServiceCreator extends AutoCloseable {

    UserService getUserService() throws ServiceCreatorException;

    EmployeeService getEmployeeService() throws ServiceCreatorException;

    BrigadeService getBrigadeService() throws ServiceCreatorException;

    FlightService getFlightService() throws ServiceCreatorException;


    UserDao getUserDao() throws ServiceCreatorException;

    EmployeeDao getEmployeeDao() throws ServiceCreatorException;

    BrigadeDao getBrigadeDao() throws ServiceCreatorException;

    FlightDao getFlightDao() throws ServiceCreatorException;

    Transaction getTransaction() throws ServiceCreatorException;

    Connection getConnection() throws ServiceCreatorException;

}
