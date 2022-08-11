package com.company.hotelBooking.dao.connection;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Class with methods for creating and processing a connection pool
 */
@Log4j2
public class ConnectionPool {
    private static final int POOL_SIZE = 100;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenAwayConnections;

    ConnectionPool(String driver, String url, String login, String password) {
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        try {
            Class.forName(driver);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, login, password);
                freeConnections.offer(new ProxyConnection(connection));
                log.info("Database connection created. Time = {}", new Date());
            }
        } catch (ClassNotFoundException | SQLException var7) {
            log.error("Error connecting to database", var7);
        }
    }

    /**
     * Method gets connection
     *
     * @return connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
            log.info("Connection got. Time = {}", new Date());
        } catch (InterruptedException var3) {
            log.error("Error getting connection", var3);
        }
        return connection;
    }

    /**
     * Method releases connection
     *
     * @param connection existing connection
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxy && givenAwayConnections.remove(connection)) {
            freeConnections.offer(proxy);
            log.info("Connection was released. Time = {}", new Date());
        } else {
            log.warn("Returned not proxy connection. Time = {}", new Date());
        }
    }

    /**
     * Method destroys pool connection
     */
    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
                log.info("Connection closed. Time = {}", new Date());
            } catch (InterruptedException var3) {
                log.error("Error destroying the connection", var3);
            }
            deregisterDrivers();
        }
    }

    /**
     * Method deregisters database driver
     */
    public void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining((driver) -> {
            try {
                DriverManager.deregisterDriver(driver);
                log.info("Driver = {} was deregistered. Time = {}", driver, new Date());
            } catch (SQLException var2) {
                log.error("Error driver deregistration", var2);
            }
        });
    }
}