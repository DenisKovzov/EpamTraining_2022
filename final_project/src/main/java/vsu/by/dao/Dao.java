package vsu.by.dao;

import vsu.by.models.Entity;

/**
 * Дао интерфейс определяющий операции свойственные
 * всем дао сущностям
 *
 * @author Kovzov Denis
 */
public interface Dao<T extends Entity> {

    Long create(T entity) throws DaoException;

    T read(Long id) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Long id) throws DaoException;
}
