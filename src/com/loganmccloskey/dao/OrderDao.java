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
import com.loganmccloskey.domain.Order;
import com.loganmccloskey.domain.OrderItem;

public class OrderDao implements BaseDao<Order> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private DatabaseConfig databaseConfig;

	public OrderDao() {
		databaseConfig = DatabaseConfig.getInstance();
	}

	private final String SELECT_ONE_ID = "SELECT id, customer_id, order_date, total_amount FROM orders WHERE id = ?";
	private final String SELECT_ONE_ID_FULL = "SELECT orders.id, customer_id, order_date, total_amount, order_items.id, order_items.order_id, unit_price, quantity FROM orders"
			+ " LEFT JOIN order_items ON orders.id = order_items.order_id	 WHERE orders.id = ?";
	private final String SELECT_ALL = "SELECT id, customer_id, order_date, total_amount FROM orders";
	private final String SELECT_ALL_FULL = "SELECT orders.id, customer_id, order_date, total_amount, order_items.id, order_items.order_id, unit_price, quantity FROM orders"
			+ " LEFT JOIN order_items ON orders.id = order_items.order_id ORDER BY orders.id";
	private final String INSERT_ONE = "INSERT INTO orders (customer_id, total_amount) VALUES (?,?)";
	private final String UPDATE_ONE = "UPDATE orders SET customer_id = ?, total_amount = ? WHERE id = ?";
	private final String DELETE_ONE = "DELETE FROM orders WHERE id = ?";

	private final String INSERT_ONE_ITEM = "INSERT INTO order_items (order_id, unit_price, quantity) VALUES (?,?,?)";
	private final String UPDATE_ONE_ITEM = "UPDATE order_items SET unit_price = ?, quantity = ? WHERE id = ?";

	@Override
	public Order selectOne(long id) {
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

	public Order selectOneFull(long id) {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection()
				.prepareStatement(SELECT_ONE_ID_FULL)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return mapperOneFull(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<Order> selectAll() {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(SELECT_ALL)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return mapperMany(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public List<Order> selectAllFull() {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(SELECT_ALL_FULL)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return mapperManyFull(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void postOne(Order obj) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(INSERT_ONE)) {
			preparedStatement.setLong(1, obj.getCustomerId());
			preparedStatement.setDouble(2, obj.getTotalAmount());
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

	public void postOneFull(Order obj) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(INSERT_ONE)) {
			preparedStatement.setLong(1, obj.getCustomerId());
			preparedStatement.setDouble(2, obj.getTotalAmount());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				for (OrderItem orderItem : obj.getOrderItems()) {
					try (PreparedStatement preparedStatementItem = databaseConfig.getConnection()
							.prepareStatement(INSERT_ONE_ITEM)) {
						preparedStatementItem.setLong(1, resultSet.getLong(1));
						preparedStatementItem.setDouble(2, orderItem.getUnitPrice());
						preparedStatementItem.setInt(3, orderItem.getQuantity());
						preparedStatementItem.executeUpdate();
					}
				}
			}
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
	public void putOne(long id, Order obj) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(UPDATE_ONE)) {
			preparedStatement.setLong(1, obj.getCustomerId());
			preparedStatement.setDouble(2, obj.getTotalAmount());
			preparedStatement.setLong(3, id);
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

	public void putOneFull(long id, Order obj) throws SQLException {
		try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(UPDATE_ONE)) {
			preparedStatement.setLong(1, obj.getCustomerId());
			preparedStatement.setDouble(2, obj.getTotalAmount());
			preparedStatement.setLong(3, id);
			preparedStatement.executeUpdate();
			for (OrderItem orderItem : obj.getOrderItems()) {
				try (PreparedStatement preparedStatementItem = databaseConfig.getConnection()
						.prepareStatement(UPDATE_ONE_ITEM)) {
					preparedStatementItem.setDouble(1, orderItem.getUnitPrice());
					preparedStatementItem.setInt(2, orderItem.getQuantity());
					preparedStatementItem.setLong(3, orderItem.getId());
					preparedStatementItem.executeUpdate();
				}
			}
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
	public Order mapperOne(ResultSet resultSet) {
		Order order = new Order();
		try {
			while (resultSet.next()) {
				order.setId(resultSet.getLong(1));
				order.setCustomerId(resultSet.getLong(2));
				order.setOrderDate(resultSet.getDate(3));
				order.setTotalAmount(resultSet.getDouble(4));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return order;
	}

	public Order mapperOneFull(ResultSet resultSet) {
		Order order = new Order();
		try {
			while (resultSet.next()) {
				OrderItem orderItem = new OrderItem();
				order.setId(resultSet.getLong(1));
				order.setCustomerId(resultSet.getLong(2));
				order.setOrderDate(resultSet.getDate(3));
				order.setTotalAmount(resultSet.getDouble(4));
				orderItem.setId(resultSet.getLong(5));
				orderItem.setOrderId(resultSet.getLong(6));
				orderItem.setUnitPrice(resultSet.getDouble(7));
				orderItem.setQuantity(resultSet.getInt(8));
				order.setOrderItem(orderItem);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return order;
	}

	@Override
	public List<Order> mapperMany(ResultSet resultSet) {
		List<Order> orders = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getLong(1));
				order.setCustomerId(resultSet.getLong(2));
				order.setOrderDate(resultSet.getDate(3));
				order.setTotalAmount(resultSet.getDouble(4));
				orders.add(order);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return orders;
	}

	public List<Order> mapperManyFull(ResultSet resultSet) {
		List<Order> orders = new ArrayList<>();
		Order order = null;
		long currentOrder = 0L;
		try {
			while (resultSet.next()) {
				if (currentOrder != resultSet.getLong(1)) {
					currentOrder = resultSet.getLong(1);
					order = new Order();
					orders.add(order);
				}
				OrderItem orderItem = new OrderItem();
				order.setId(resultSet.getLong(1));
				order.setCustomerId(resultSet.getLong(2));
				order.setOrderDate(resultSet.getDate(3));
				order.setTotalAmount(resultSet.getDouble(4));
				orderItem.setId(resultSet.getLong(5));
				orderItem.setOrderId(resultSet.getLong(6));
				orderItem.setUnitPrice(resultSet.getDouble(7));
				orderItem.setQuantity(resultSet.getInt(8));
				order.setOrderItem(orderItem);
				orders.set(orders.indexOf(order), order);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return orders;
	}
}
