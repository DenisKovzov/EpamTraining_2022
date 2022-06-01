package vsu.by.dao.mysql;

import vsu.by.dao.DaoException;
import vsu.by.dao.UserDao;
import vsu.by.models.Role;
import vsu.by.models.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация дао интерфейса пользователя для mysql
 *
 * @author Kovzov Denis
 * @see vsu.by.dao.UserDao
 */
public class UserDaoImpl implements UserDao {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(User entity) throws DaoException {

        String sql = "INSERT INTO `users` (`login`, `password`, `role` ) VALUES(?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole().ordinal());
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
                connection.close();
            } catch (Exception e) {
            }

        }
    }

    @Override
    public User read(Long id) throws DaoException {
        String sql = "SELECT `login`, `password`, `role` FROM `users` WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                user = new User();

                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);

            }
            return user;

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
    public User readByLogin(String login) throws DaoException {
        String sql = "SELECT `id`, `login`, `password`, `role` FROM `users` WHERE login = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);

            resultSet = statement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                user = new User();

                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);

            }
            return user;

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
    public void update(User entity) throws DaoException {
        String sql = "UPDATE `users` SET `login` = ?, `password` = ?, `role` = ? WHERE id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole().ordinal());
            statement.setLong(4, entity.getId());

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
        String sql = "DELETE FROM `users` WHERE id = ?";
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
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        String sql = "SELECT `id`, `role` FROM `users` WHERE `login` = ? AND `password` = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                user = new User();

                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;

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
    public List<User> readAll() throws DaoException {
        String sql = "SELECT `id`, `login`, `password`, `role` FROM `users`";

        List<User> users = new LinkedList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);

                users.add(user);
            }
            return users;

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

}
