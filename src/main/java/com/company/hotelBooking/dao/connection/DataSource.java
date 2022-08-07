package com.company.hotelBooking.dao.connection;

import com.company.hotelBooking.util.ConfigurationManager;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.util.Date;

/**
 * Class with methods for connecting and interacting with the database
 */
@Log4j2
public class DataSource implements AutoCloseable {
	private static DataSource INSTANCE;
	private ConnectionPool connectionPool;
	private final String url;
	private final String login;
	private final String password;
	private final String driver;

	/**
	 * Method gets an instance of the class object
	 * 
	 * @return an instance of the class object
	 */
	public static DataSource getINSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new DataSource();
		}

		return INSTANCE;
	}

	public DataSource() {
		url = ConfigurationManager.getInstance().getString(ConfigurationManager.DB_URL);
		login = ConfigurationManager.getInstance().getString(ConfigurationManager.DB_LOGIN);
		password = ConfigurationManager.getInstance().getString(ConfigurationManager.DB_PASSWORD);
		driver = ConfigurationManager.getInstance().getString(ConfigurationManager.DB_DRIVER);
	}

	/**
	 * Method gets connection
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		if (connectionPool == null) {
			connectionPool = new ConnectionPool(driver, url, login, password);
			log.info("Connection pool initialized. Time = {}", new Date());
		}

		return connectionPool.getConnection();
	}

	ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	/**
	 * Method for closing connection
	 */
	@Override
	public void close() {
		if (connectionPool != null) {
			connectionPool.destroyPool();
			connectionPool = null;
			log.info("Database connection pool was destroyed. Time = {}", new Date());
		}
	}
}