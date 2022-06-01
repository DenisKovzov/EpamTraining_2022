package vsu.by.services;

import vsu.by.models.Flight;
import vsu.by.services.exceptions.ServiceException;

import java.util.List;

/**
 * Класс для обработки бизнес-логики рейса
 *
 * @author Kovzov Denis
 */
public interface FlightService {
    List<Flight> findAll() throws ServiceException;

    Flight findById(Long id) throws ServiceException;

    void save(Flight flight) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
