package com.marko.springboot.crudtest.service;

import java.util.List;

public interface ServiceInterface<T> {
	
	public List<T>findAll();
	
	public T findById(int theId);

	public void save(T entity);

	public void deleteById(int theId);

}
