package vsu.by.services.exceptions;

/**
 * Исключение для Transaction
 *
 * @author Kovzov Denis
 */
public class TransactionException extends ServiceException {
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
