package vsu.by.services;

import vsu.by.models.Employee;
import vsu.by.services.exceptions.ServiceException;

import java.util.List;

/**
 * Класс для обработки бизнес-логики сотрудника
 *
 * @author Kovzov Denis
 */
public interface EmployeeService {
    List<Employee> findAll() throws ServiceException;

    List<Employee> findAllByIds(List<Long> ids) throws ServiceException;

    List<Employee> findAllFree() throws ServiceException;

    Employee findById(Long id) throws ServiceException;

    void save(Employee employee) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;
}
