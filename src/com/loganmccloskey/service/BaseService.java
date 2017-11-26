package com.loganmccloskey.service;

import java.util.List;

public interface BaseService<T> {
	T findOne(long id);
	List<T> findAll();
	void createOne(T obj);
	void updateOne(long id, T obj);
	void deleteOne(long id);
}
