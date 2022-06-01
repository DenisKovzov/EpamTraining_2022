package vsu.by.pool;

/**
 * Исключение для ConnectionPool
 *
 * @author Kovzov Denis
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
