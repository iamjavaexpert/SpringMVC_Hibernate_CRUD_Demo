package com.springmvc.hibernate.training.project.dao;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.springmvc.hibernate.training.project.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAOImpl.
 */
@Repository("userDao")
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class.getName());

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springmvc.hibernate.training.project.dao.UserDAO#
	 * findUserByUsernamePassword(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByUsernamePassword(String username, String password) {
		log.info("userDao : Executing findUserByUsernamePassword method");

		Query<User> query = getSession().getNamedQuery("findUserByUsernamePassword");
		query.setParameter(0, username);
		query.setParameter(1, password);

		return query.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springmvc.hibernate.training.project.dao.UserDAO#
	 * findUserByEmailAddress(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByEmailAddress(String emailAddress) {
		log.info("userDao : Executing findUserByEmailAddress method");

		Query<User> query = getSession().getNamedQuery("findUserByEmailAddress");
		query.setParameter(0, emailAddress);

		return query.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.dao.UserDAO#updatePassword(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int updatePassword(String emailAddress, String password) {
		log.info("userDao: Executing updatePassword method");
		Query<User> query = getSession().createQuery("update User set password=? where emailAddress=?");
		query.setParameter(0, password);
		query.setParameter(1, emailAddress);

		return query.executeUpdate();
	}

}
