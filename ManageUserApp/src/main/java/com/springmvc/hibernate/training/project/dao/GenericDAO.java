package com.springmvc.hibernate.training.project.dao;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface GenericDAO.
 *
 * @param <T>
 *            the generic type
 */
public interface GenericDAO<T> {

	/**
	 * add entity into the database.
	 *
	 * @param t
	 *            the t
	 */
	void add(T t);

	/**
	 * update entity.
	 *
	 * @param t
	 *            the t
	 */
	void update(T t);

	/**
	 * get list of all entities.
	 *
	 * @return the list
	 */
	List<T> list();

	/**
	 * find entity by Id.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	T findById(long id);

	/**
	 * remove entitiy from the database.
	 *
	 * @param t
	 *            the t
	 */
	void remove(T t);

	/**
	 * remove entity by Id.
	 *
	 * @param id
	 *            the id
	 * @return the int
	 */
	int removeById(long id);
}
