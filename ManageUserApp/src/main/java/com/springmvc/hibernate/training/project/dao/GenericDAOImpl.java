package com.springmvc.hibernate.training.project.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericDAOImpl.
 *
 * @param <T>
 *            the generic type
 */
@Repository("genericDao")
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(GenericDAOImpl.class.getName());

	/** The session factory. */
	private SessionFactory sessionFactory;

	/** The clazz. */
	private Class<T> clazz;

	/**
	 * Instantiates a new generic DAO impl.
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		log.info("Executing GenericDAOImpl Initialization");
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Sets the session factory.
	 *
	 * @param sessionFactory
	 *            the new session factory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.dao.GenericDAO#add(java.lang.
	 * Object)
	 */
	@Override
	public void add(T t) {
		log.info("Executing Generic add method");
		getSession().save(t);
		log.info("Entity record Saved into the Database." + t);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.dao.GenericDAO#update(java.lang.
	 * Object)
	 */
	@Override
	public void update(T t) {
		log.info("Executing Generic update method");
		getSession().update(t);
		log.info("Entity record updated into the Database." + t);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.dao.GenericDAO#remove(java.lang.
	 * Object)
	 */
	@Override
	public void remove(T t) {
		log.info("Executing Generic remove method");
		getSession().delete(t);
		log.info("Entity record deleted from the Database." + t);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springmvc.hibernate.training.project.dao.GenericDAO#list()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> list() {
		log.info("Executing Generic list method");
		return getSession().createQuery("from " + clazz.getName()).list();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.dao.GenericDAO#findById(long)
	 */
	@Override
	public T findById(long id) {
		log.info("Executing Generic findById method");
		return getSession().load(clazz, id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.dao.GenericDAO#removeById(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int removeById(long id) {
		log.info("Executing Generic removeById method");
		Query<T> query = getSession().createQuery("delete from " + clazz.getName() + " where id=:id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public Session getSession() {
		Session session = this.sessionFactory.getCurrentSession();
		if (session == null) {
			session = this.sessionFactory.openSession();
		}
		return session;
	}
}
