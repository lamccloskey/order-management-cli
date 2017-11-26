package com.loganmccloskey.common;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class DatabaseConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static DatabaseConfig getInstance() {
		if (singletonInstance == null) {
			return new DatabaseConfig();
		}

		return singletonInstance;
	}
	
	private static DatabaseConfig singletonInstance = null;
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}
	
	private DatabaseConfig() {
		setJdbcDriver();
		createConnection();
	}

	private void setJdbcDriver() {
		try {
			Class.forName(System.getenv("JDBC_DRIVER"));
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void createConnection() {
		try {
			connection = DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("USER"), System.getenv("PASSWORD"));
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
