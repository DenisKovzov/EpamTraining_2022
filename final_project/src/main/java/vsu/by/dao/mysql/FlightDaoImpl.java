package vsu.by.dao.mysql;

import vsu.by.dao.DaoException;
import vsu.by.dao.FlightDao;
import vsu.by.models.Brigade;
import vsu.by.models.Flight;
import vsu.by.models.Status;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация дао интерфейса рейса для mysql
 *
 * @author Kovzov Denis
 * @see vsu.by.dao.FlightDao
 */
public class FlightDaoImpl implements FlightDao {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean haveBrigade(Long brigadeId) throws DaoException {
        String sql = "SELECT COUNT(*) as `count` FROM `flights` WHERE id_brigade = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        boolean haveBrigade = false;

        try {
            statement = connection.prepareStatement(sql);

            statement.setLong(1, brigadeId);

            statement.executeQuery();

            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                haveBrigade = resultSet.getBoolean("count");
            }
            return haveBrigade;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long create(Flight entity) throws DaoException {
        String sql = "INSERT INTO `flights` (`number_tickets`, `date_time_departure`, `date_time_arrival`, `departure_point`, `destination`, `status`, `id_brigade`) VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getNumberTickets());
            statement.setTimestamp(2, new Timestamp(entity.getDepartureTime().getTime()));
            statement.setTimestamp(3, new Timestamp(entity.getArrivalTime().getTime()));
            statement.setString(4, entity.getDeparturePoint());
            statement.setString(5, entity.getDestination());
            statement.setInt(6, entity.getStatus().ordinal());
            statement.setObject(7, entity.getBrigade().getId(), Types.BIGINT);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }

        }
    }

    @Override
    public Flight read(Long id) throws DaoException {
        String sql = "SELECT `number_tickets`, `date_time_departure`, `date_time_arrival`, `departure_point`, `destination`, `status`, `id_brigade` FROM `flights` WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            Flight flight = null;

            if (resultSet.next()) {
                flight = new Flight();

                flight.setId(id);
                flight.setNumberTickets(resultSet.getInt("number_tickets"));
                flight.setDepartureTime(new Date(resultSet.getTimestamp("date_time_departure").getTime()));
                flight.setArrivalTime(new Date(resultSet.getTimestamp("date_time_arrival").getTime()));
                flight.setDeparturePoint(resultSet.getString("departure_point"));
                flight.setDestination(resultSet.getString("destination"));
                flight.setStatus(Status.values()[resultSet.getInt("status")]);

                Brigade brigade = new Brigade();
                Long brigadeId = resultSet.getLong("id_brigade");
                if (!resultSet.wasNull()) {
                    brigade.setId(brigadeId);
                }

                flight.setBrigade(brigade);
            }
            return flight;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }

            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Flight> readAll() throws DaoException {
        String sql = "SELECT `id`, `number_tickets`, `date_time_departure`, `date_time_arrival`, `departure_point`, `destination`, `status`, `id_brigade` FROM `flights`";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            List<Flight> flights = new LinkedList<>();

            while (resultSet.next()) {
                Flight flight = new Flight();

                flight.setId(resultSet.getLong("id"));
                flight.setNumberTickets(resultSet.getInt("number_tickets"));
                flight.setDepartureTime(new Date(resultSet.getTimestamp("date_time_departure").getTime()));
                flight.setArrivalTime(new Date(resultSet.getTimestamp("date_time_arrival").getTime()));
                flight.setDeparturePoint(resultSet.getString("departure_point"));
                flight.setDestination(resultSet.getString("destination"));
                flight.setStatus(Status.values()[resultSet.getInt("status")]);

                Brigade brigade = new Brigade();
                ;
                Long brigadeId = resultSet.getLong("id_brigade");
                if (!resultSet.wasNull()) {
                    brigade.setId(brigadeId);
                }

                flight.setBrigade(brigade);

                flights.add(flight);
            }
            return flights;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }

            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(Flight entity) throws DaoException {
        String sql = "UPDATE `flights` SET `number_tickets` = ?, `date_time_departure` = ?, `date_time_arrival` = ?, `departure_point` = ?, `destination` = ?, `status` = ?, `id_brigade` = ? WHERE id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getNumberTickets());
            statement.setTimestamp(2, new Timestamp(entity.getDepartureTime().getTime()));
            statement.setTimestamp(3, new Timestamp(entity.getArrivalTime().getTime()));
            statement.setString(4, entity.getDeparturePoint());
            statement.setString(5, entity.getDestination());
            statement.setInt(6, entity.getStatus().ordinal());
            statement.setObject(7, entity.getBrigade().getId(), Types.BIGINT);
            statement.setLong(8, entity.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `flights` WHERE id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
}
