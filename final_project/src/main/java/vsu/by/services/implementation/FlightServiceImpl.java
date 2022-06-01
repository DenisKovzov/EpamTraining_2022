package vsu.by.services.implementation;

import vsu.by.dao.BrigadeDao;
import vsu.by.dao.DaoException;
import vsu.by.dao.FlightDao;
import vsu.by.models.Flight;
import vsu.by.models.Status;
import vsu.by.services.FlightService;
import vsu.by.services.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса FlightService
 *
 * @author Kovzov Denis
 * @see vsu.by.services.FlightService
 */
public class FlightServiceImpl implements FlightService {
    private FlightDao flightDao;
    private BrigadeDao brigadeDao;

    public void setBrigadeDao(BrigadeDao brigadeDao) {
        this.brigadeDao = brigadeDao;
    }


    public void setFlightDao(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public Flight findById(Long id) throws ServiceException {
        try {
            Flight flight = flightDao.read(id);
            flight.setBrigade(brigadeDao.read(flight.getBrigade().getId()));
            return flight;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<Flight> findAll() throws ServiceException {
        try {
            List<Flight> flights = new ArrayList<>(flightDao.readAll());

            for (Flight flight : flights) {
                flight.setBrigade(brigadeDao.read(flight.getBrigade().getId()));
            }
            return flights;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean isBrigadeBusy(Flight flight) {
        boolean isBrigadeBusy = false;
        try {
            List<Flight> flights = findAll().stream().filter(e ->
                    e.getBrigade().getId() == flight.getBrigade().getId() && e.getId() != flight.getId()).collect(Collectors.toList());

            isBrigadeBusy = flights.stream().anyMatch(e ->
                    (e.getDepartureTime().after(flight.getDepartureTime()) && e.getDepartureTime().before(flight.getArrivalTime())) ||
                            (e.getArrivalTime().after(flight.getDepartureTime()) && e.getArrivalTime().before(flight.getArrivalTime())) ||
                            (flight.getArrivalTime().after(e.getDepartureTime()) && flight.getArrivalTime().before(e.getArrivalTime()))
            );

            return isBrigadeBusy;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Flight flight) throws ServiceException {
        try {
            if (flight.getArrivalTime().compareTo(flight.getDepartureTime()) < 0) {
                throw new ServiceException("time is wrong");
            }

            if (isBrigadeBusy(flight)) {
                throw new ServiceException("this brigade is busy at this time");
            }

            if (flight.getId() != null) {
                if (flight.getStatus() == Status.ACTIVE) {
                    flight.setStatus(Status.CHANGED);
                }

                flightDao.update(flight);
            } else {
                flight.setStatus(Status.NO_ACTIVE);
                flightDao.create(flight);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            Flight flight = flightDao.read(id);
            if (flight.getStatus() == Status.NO_ACTIVE) {
                flightDao.delete(id);
            } else {
                flight.setStatus(Status.CANCELED);
                flightDao.update(flight);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
