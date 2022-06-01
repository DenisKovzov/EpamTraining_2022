package vsu.by.services.implementation;

import vsu.by.services.Transaction;
import vsu.by.services.exceptions.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Реализация интерфейса Transaction
 *
 * @author Kovzov Denis
 * @see vsu.by.services.Transaction
 */
public class TransactionImpl implements Transaction {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void start() throws TransactionException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }
}
