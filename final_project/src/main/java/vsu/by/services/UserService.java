package vsu.by.services;

import vsu.by.models.User;
import vsu.by.services.exceptions.ServiceException;

import java.util.List;

/**
 * Класс для обработки бизнес-логики пользователя
 *
 * @author Kovzov Denis
 */
public interface UserService {
    List<User> findAll() throws ServiceException;

    User findById(Long id) throws ServiceException;

    User login(String login, String password) throws ServiceException;

    void save(User user) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    List<User> findAllIsNotEmployee() throws ServiceException;
}
