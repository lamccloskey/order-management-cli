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
import com.loganmccloskey.domain.OrderItem;

public class OrderItemDao implements BaseDao<OrderItem> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private DatabaseConfig databaseConfig;

	public OrderItemDao() {
		databaseConfig = DatabaseConfig.getInstance();
	}

	private final String SELECT_ONE_ID = "SELECT id, order_id, unit_price, quantity FROM order_items WHERE id = ?";
	private final String SELECT_ALL = "SELECT id, order_id, unit_price, quantity FROM order_items";
	private final String INSERT_ONE = "INSERT INTO order_items (order_id, unit_price, quantity) VALUES (?,?,?)";
	private final String UPDATE_ONE = "UPDATE order_items SET order_id = ?, unit_price = ?, quantity = ? WHERE id = ?";
	private final String DELETE_ONE = "DELETE FROM order_items WHERE id = ?";

	@Override
	public OrderItem selectOne(long id) {
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

	@Override
	public List<OrderItem> selectAll() {
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
	public void postOne(OrderItem obj) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(INSERT_ONE)) {
			preparedStatement.setLong(1, obj.getOrderId());
			preparedStatement.setDouble(2, obj.getUnitPrice());
			preparedStatement.setInt(3, obj.getQuantity());
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
	public void putOne(long id, OrderItem obj) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(UPDATE_ONE)) {
			preparedStatement.setLong(1, obj.getOrderId());
			preparedStatement.setDouble(2, obj.getUnitPrice());
			preparedStatement.setInt(3, obj.getQuantity());
			preparedStatement.setLong(4, id);
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
	public OrderItem mapperOne(ResultSet resultSet) {
		OrderItem orderItem = new OrderItem();
		try {
			while (resultSet.next()) {
				orderItem.setId(resultSet.getLong(1));
				orderItem.setOrderId(resultSet.getLong(2));
				orderItem.setUnitPrice(resultSet.getDouble(3));
				orderItem.setQuantity(resultSet.getInt(4));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return orderItem;
	}

	@Override
	public List<OrderItem> mapperMany(ResultSet resultSet) {
		List<OrderItem> orderItems = new ArrayList<>();
		try {
			while (resultSet.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(resultSet.getLong(1));
				orderItem.setOrderId(resultSet.getLong(2));
				orderItem.setUnitPrice(resultSet.getDouble(3));
				orderItem.setQuantity(resultSet.getInt(4));
				orderItems.add(orderItem);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return orderItems;
	}

}
