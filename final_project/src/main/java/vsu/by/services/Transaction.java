package vsu.by.services;

import vsu.by.services.exceptions.TransactionException;
/**
 * Класс для создания транзакций при работе с dao классами
 *
 * @author Kovzov Denis
 */
public interface Transaction {
    void start() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
