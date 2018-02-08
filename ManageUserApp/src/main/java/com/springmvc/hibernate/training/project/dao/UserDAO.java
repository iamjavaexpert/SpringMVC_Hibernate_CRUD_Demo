package com.springmvc.hibernate.training.project.dao;

import com.springmvc.hibernate.training.project.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDAO.
 */
public interface UserDAO extends GenericDAO<User> {

	/**
	 * find user by username and password.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the user
	 */
	User findUserByUsernamePassword(String username, String password);

	/**
	 * find user by email address.
	 *
	 * @param emailAddress
	 *            the email address
	 * @return the user
	 */
	User findUserByEmailAddress(String emailAddress);

	/**
	 * update user password by email address.
	 *
	 * @param emailAddress
	 *            the email address
	 * @param password
	 *            the password
	 * @return the int
	 */
	int updatePassword(String emailAddress, String password);
}
