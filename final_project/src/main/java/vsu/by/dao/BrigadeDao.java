package vsu.by.dao;

import vsu.by.models.Brigade;

import java.util.List;

/**
 * Дао интерфейс для бригады
 *
 * @author Kovzov Denis
 */
public interface BrigadeDao extends Dao<Brigade> {
    List<Brigade> readAll() throws DaoException;

    boolean isNameExist(String name) throws DaoException;
}
