package vsu.by.services;

import vsu.by.models.Brigade;
import vsu.by.services.exceptions.ServiceException;

import java.util.List;

/**
 * Класс для обработки бизнес-логики бригады
 *
 * @author Kovzov Denis
 */
public interface BrigadeService {
    List<Brigade> findAll() throws ServiceException;

    Brigade findById(Long id) throws ServiceException;

    void save(Brigade brigade) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
