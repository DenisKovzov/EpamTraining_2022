package vsu.by.dao.mysql;

import vsu.by.dao.DaoException;
import vsu.by.dao.EmployeeDao;
import vsu.by.models.Employee;
import vsu.by.models.Position;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация дао интерфейса сотрудника для mysql
 *
 * @author Kovzov Denis
 * @see vsu.by.dao.EmployeeDao
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(Employee entity) throws DaoException {
        String sql = "INSERT INTO `employees` (`id`, `position`, `id_brigade`) VALUES (?, ?, ?)";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getId());
            statement.setInt(2, entity.getPosition().ordinal());
            statement.setObject(3, entity.getBrigadeId(), Types.BIGINT);

            statement.executeUpdate();

            return entity.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }

        }
    }

    @Override
    public Employee read(Long id) throws DaoException {
        String sql = "SELECT `position`, `id_brigade` FROM `employees` WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            Employee employee = null;

            if (resultSet.next()) {
                employee = new Employee();

                employee.setId(id);
                employee.setPosition(Position.values()[resultSet.getInt(1)]);
                employee.setBrigadeId(resultSet.getLong(2));
                if (resultSet.wasNull()) {
                    employee.setBrigadeId(null);
                }

            }
            return employee;

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
    public void update(Employee entity) throws DaoException {
        String sql = "UPDATE `employees` SET `position` = ?, `id_brigade` = ? WHERE id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getPosition().ordinal());
            statement.setObject(2, entity.getBrigadeId(), Types.BIGINT);
            statement.setLong(3, entity.getId());

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
        String sql = "DELETE FROM `employees` WHERE id = ?";
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

    @Override
    public List<Employee> readAll() throws DaoException {
        String sql = "SELECT `id`, `position`, `id_brigade` FROM `employees`";

        List<Employee> employees = new LinkedList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setId(resultSet.getLong("id"));
                employee.setPosition(Position.values()[resultSet.getInt("position")]);
                employee.setBrigadeId(resultSet.getLong("id_brigade"));
                if (resultSet.wasNull()) {
                    employee.setBrigadeId(null);
                }

                employees.add(employee);
            }
            return employees;

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
    public List<Employee> readAllByBrigadeId(Long brigadeId) throws DaoException {
        String sql = "SELECT `id`, `position` FROM `employees` WHERE id_brigade=?";

        List<Employee> employees = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, brigadeId);

            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setId(resultSet.getLong("id"));
                employee.setPosition(Position.values()[resultSet.getInt("position")]);
                employee.setBrigadeId(brigadeId);

                employees.add(employee);
            }
            return employees;

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
    public boolean isExist(Long id) {
        String sql = "SELECT COUNT(*) as `count` FROM `employees` WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        boolean isExist = false;

        try {
            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            statement.executeQuery();

            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                isExist = resultSet.getBoolean("count");
            }
            return isExist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
