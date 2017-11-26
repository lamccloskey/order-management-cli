package com.loganmccloskey.service;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loganmccloskey.dao.OrderItemDao;
import com.loganmccloskey.domain.OrderItem;

public class OrderItemService implements BaseService<OrderItem> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private OrderItemDao dao;
	
	public OrderItemService() {
		dao = new OrderItemDao();
	}

	@Override
	public OrderItem findOne(long id) {
		return dao.selectOne(id);
	}

	@Override
	public List<OrderItem> findAll() {
		return dao.selectAll();
	}

	@Override
	public void createOne(OrderItem obj) {
		try {
			dao.postOne(obj);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void updateOne(long id, OrderItem obj) {
		try {
			dao.putOne(id, obj);
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
