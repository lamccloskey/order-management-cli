package com.loganmccloskey.service;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loganmccloskey.dao.OrderDao;
import com.loganmccloskey.domain.Order;

public class OrderService implements BaseService<Order> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private OrderDao dao;
	
	public OrderService() {
		dao = new OrderDao();
	}

	@Override
	public Order findOne(long id) {
		return dao.selectOne(id);
	}
	
	public Order findOneFull(long id) {
		return dao.selectOneFull(id);
	}

	@Override
	public List<Order> findAll() {
		return dao.selectAll();
	}
	
	public List<Order> findAllFull() {
		return dao.selectAllFull();
	}

	@Override
	public void createOne(Order obj) {
		try {
			dao.postOne(obj);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public void createOneFull(Order obj) {
		try {
			dao.postOneFull(obj);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void updateOne(long id, Order obj) {
		try {
			dao.putOne(id, obj);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public void updateOneFull(long id, Order obj) {
		try {
			dao.putOneFull(id, obj);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteOne(long id) {
		try {
			dao.deleteOne(id);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
