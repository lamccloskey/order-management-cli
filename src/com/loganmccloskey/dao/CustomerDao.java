package com.loganmccloskey.dao;

import java.lang.invoke.MethodHandles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loganmccloskey.common.DatabaseConfig;
import com.loganmccloskey.domain.Customer;

public class CustomerDao implements BaseDao<Customer> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private DatabaseConfig databaseConfig;

	public CustomerDao() {
		databaseConfig = DatabaseConfig.getInstance();
	}

	private final String SELECT_ONE_ID = "SELECT id, first_name, last_name, city, country, phone FROM customers WHERE id = ?";
	private final String SELECT_ONE_FN_LN = "SELECT id, first_name, last_name, city, country, phone FROM customers WHERE first_name = ? and last_name = ?";
	private final String SELECT_ALL = "SELECT id, first_name, last_name, city, country, phone FROM customers";
	private final String INSERT_ONE = "INSERT INTO customers (first_name, last_name, city, country, phone) VALUES (?,?,?,?,?)";
	private final String UPDATE_ONE = "UPDATE customers SET first_name = ?, last_name = ?, city = ?, country = ?, phone = ? WHERE id = ?";
	private final String DELETE_ONE = "DELETE FROM customers WHERE id = ?";

	@Override
	public Customer selectOne(long id) {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(SELECT_ONE_ID)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return mapperOne(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	public Customer selectOne(String firstName, String lastName) {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(SELECT_ONE_FN_LN)) {
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return mapperOne(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public List<Customer> selectAll() {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(SELECT_ALL)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return mapperMany(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public void postOne(Customer obj) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(INSERT_ONE)) {
			preparedStatement.setString(1, obj.getFirstName());
			preparedStatement.setString(2, obj.getLastName());
			preparedStatement.setString(3, obj.getCity());
			preparedStatement.setString(4, obj.getCountry());
			preparedStatement.setString(5, obj.getPhone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			if (databaseConfig.getConnection() != null) {
				databaseConfig.getConnection().rollback();
			}
			LOGGER.error(e.getMessage(), e);
		} finally {
			databaseConfig.getConnection().commit();
		}
	}

	@Override
	public void putOne(long id, Customer obj) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(UPDATE_ONE)) {
			preparedStatement.setString(1, obj.getFirstName());
			preparedStatement.setString(2, obj.getLastName());
			preparedStatement.setString(3, obj.getCity());
			preparedStatement.setString(4, obj.getCountry());
			preparedStatement.setString(5, obj.getPhone());
			preparedStatement.setLong(6, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			if (databaseConfig.getConnection() != null) {
				databaseConfig.getConnection().rollback();
			}
			LOGGER.error(e.getMessage(), e);
		} finally {
			databaseConfig.getConnection().commit();
		}
	}

	@Override
	public void deleteOne(long id) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(DELETE_ONE)) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			if (databaseConfig.getConnection() != null) {
				databaseConfig.getConnection().rollback();
			}
			LOGGER.error(e.getMessage(), e);
		} finally {
			databaseConfig.getConnection().commit();
		}
	}

	@Override
	public Customer mapperOne(ResultSet resultSet) {
		Customer customer = new Customer();
		try {
			while (resultSet.next()) {
				customer.setId(resultSet.getLong(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setCity(resultSet.getString(4));
				customer.setCountry(resultSet.getString(5));
				customer.setPhone(resultSet.getString(6));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return customer;
	}

	@Override
	public List<Customer> mapperMany(ResultSet resultSet) {
		List<Customer> customers = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getLong(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setCity(resultSet.getString(4));
				customer.setCountry(resultSet.getString(5));
				customer.setPhone(resultSet.getString(6));
				customers.add(customer);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return customers;
	}

}
