package vsu.by.dao.mysql;

import vsu.by.dao.BrigadeDao;
import vsu.by.dao.DaoException;
import vsu.by.models.Brigade;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация дао интерфейса бригады для mysql
 *
 * @author Kovzov Denis
 * @see vsu.by.dao.BrigadeDao
 */
public class BrigadeDaoImpl implements BrigadeDao {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(Brigade entity) throws DaoException {
        String sql = "INSERT INTO `brigades` (`name`) VALUES(?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
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
    public Brigade read(Long id) throws DaoException {
        String sql = "SELECT `name` FROM `brigades` WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            Brigade brigade = null;

            if (resultSet.next()) {
                brigade = new Brigade();

                brigade.setId(id);
                brigade.setName(resultSet.getString(1));

            }
            return brigade;
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
    public List<Brigade> readAll() throws DaoException {
        String sql = "SELECT `id`, `name` FROM `brigades`";

        Statement statement = null;
        ResultSet resultSet = null;
        List<Brigade> brigades = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Brigade brigade = new Brigade();

                brigade.setId(resultSet.getLong("id"));
                brigade.setName(resultSet.getString("name"));
                brigades.add(brigade);
            }
            return brigades;

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
    public void update(Brigade entity) throws DaoException {
        String sql = "UPDATE `brigades` SET `name` = ? WHERE id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getId());

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
        String sql = "DELETE FROM `brigades` WHERE id = ?";
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
    public boolean isNameExist(String name) throws DaoException {
        String sql = "SELECT COUNT(*) as `count` FROM `brigades` WHERE name = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        boolean isExist = false;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, name);

            statement.executeQuery();

            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                isExist = resultSet.getBoolean("count");
            }
            return isExist;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
