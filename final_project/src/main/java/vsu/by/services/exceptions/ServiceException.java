package vsu.by.services.exceptions;

/**
 * Исключение для классов сервисов
 *
 * @author Kovzov Denis
 */
public class ServiceException extends Exception {
    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
