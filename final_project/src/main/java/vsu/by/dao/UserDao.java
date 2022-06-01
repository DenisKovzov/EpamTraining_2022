package vsu.by.dao;

import vsu.by.models.User;

import java.util.List;

/**
 * Дао интерфейс для пользователя
 *
 * @author Kovzov Denis
 */
public interface UserDao extends Dao<User> {

    List<User> readAll() throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;

    User readByLogin(String login) throws DaoException;
}
