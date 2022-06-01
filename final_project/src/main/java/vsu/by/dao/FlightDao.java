package vsu.by.dao;

import vsu.by.models.Flight;

import java.util.List;

/**
 * Дао интерфейс для рейса
 *
 * @author Kovzov Denis
 */
public interface FlightDao extends Dao<Flight> {
    List<Flight> readAll() throws DaoException;

    boolean haveBrigade(Long brigadeId) throws DaoException;
}
