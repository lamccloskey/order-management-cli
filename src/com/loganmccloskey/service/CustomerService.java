package com.loganmccloskey.service;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loganmccloskey.dao.CustomerDao;
import com.loganmccloskey.domain.Customer;

public class CustomerService implements BaseService<Customer> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private CustomerDao dao;
	
	public CustomerService() {
		dao = new CustomerDao();
	}

	@Override
	public Customer findOne(long id) {
		return dao.selectOne(id);
	}
	
	public Customer findOne(String firstName, String lastName) {
		return dao.selectOne(firstName, lastName);
	}

	@Override
	public List<Customer> findAll() {
		return dao.selectAll();
	}

	@Override
	public void createOne(Customer obj) {
		try {
			dao.postOne(obj);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void updateOne(long id, Customer obj) {
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
