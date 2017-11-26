package com.loganmccloskey.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao <T extends Serializable> {
	T selectOne(long id);
	List<T> selectAll();
	void postOne(T obj) throws SQLException;
	void putOne(long id, T obj) throws SQLException;
	void deleteOne(long id) throws SQLException;
	T mapperOne(ResultSet rs);
	List<T> mapperMany(ResultSet rs);
}
