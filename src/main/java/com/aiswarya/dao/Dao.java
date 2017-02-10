package com.aiswarya.dao;

import java.util.List;

import com.aiswarya.exception.PersistanceException;


public interface Dao<T> {

	void save(T t) throws PersistanceException;

	void update(T t) throws PersistanceException;

	void updateAsInactive(T t);

	List<T> listAll();


}
