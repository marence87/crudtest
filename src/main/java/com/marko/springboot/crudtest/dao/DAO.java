package com.marko.springboot.crudtest.dao;

import java.util.List;


public interface DAO<T> {
	
	public List<T> findAll();
	
	public T findById(int theId);

	public void save(T entity);

	public void deleteById(int theId);

}
