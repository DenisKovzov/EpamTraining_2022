package vsu.by.dao;

import vsu.by.models.Employee;

import java.util.List;

/**
 * Дао интерфейс для сотрудника
 *
 * @author Kovzov Denis
 */
public interface EmployeeDao extends Dao<Employee> {
    List<Employee> readAll() throws DaoException;

    List<Employee> readAllByBrigadeId(Long brigadeId) throws DaoException;

    boolean isExist(Long id);
}
