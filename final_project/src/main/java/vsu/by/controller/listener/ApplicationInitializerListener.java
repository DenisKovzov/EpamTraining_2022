package vsu.by.controller.listener;

import org.apache.logging.log4j.LogManager;
import vsu.by.pool.ConnectionPool;
import vsu.by.pool.ConnectionPoolException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Слушатель который отвечает за
 * подключения к базе данных и её разрушения
 *
 * @author Kovzov Denis
 */
public class ApplicationInitializerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();

        String driverName = context.getInitParameter("jdbc-driverName");
        String jdbcUrl = context.getInitParameter("jdbc-url");
        String user = context.getInitParameter("jdbc-user");
        String password = context.getInitParameter("jdbc-password");
        int minSize = Integer.parseInt(context.getInitParameter("jdbc-minSize"));
        int maxSize = Integer.parseInt(context.getInitParameter("jdbc-maxSize"));
        int validationConnectionTimeout = Integer.parseInt(context.getInitParameter("jdbc-validationConnectionTimeout"));

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            LogManager.getLogger().info("initialize connection pool");
            pool.init(
                    driverName,
                    jdbcUrl,
                    user,
                    password,
                    minSize,
                    maxSize,
                    validationConnectionTimeout);

        } catch (ConnectionPoolException e) {
            LogManager.getLogger().fatal(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroy();
    }
}
