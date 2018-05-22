package br.com.appfactory.easyfood.dao;

import java.util.List;

public abstract class AbstractDao<T> {

	public abstract T save(T entity);
	
	public abstract T findOne(T entity);

	public abstract T findById(Long id);
	
	public abstract List<T> findAll();
	
	public abstract void remove(T entity);
	
}
