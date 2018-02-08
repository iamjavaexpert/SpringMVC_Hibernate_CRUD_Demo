package com.springmvc.hibernate.training.project.services;

import java.util.List;

import com.springmvc.hibernate.training.project.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * add user into the database.
	 *
	 * @param user
	 *            the user
	 */
	void addUser(User user);

	/**
	 * update user details.
	 *
	 * @param user
	 *            the user
	 */
	void updateUser(User user);

	/**
	 * update user password by emailAddress.
	 *
	 * @param emailAddress
	 *            the email address
	 * @param password
	 *            the password
	 * @return the int
	 */
	int updatePassword(String emailAddress, String password);

	/**
	 * get list of all users.
	 *
	 * @return the users
	 */
	List<User> getUsers();

	/**
	 * find user by user Id.
	 *
	 * @param userId
	 *            the user id
	 * @return the user
	 */
	User findUserById(long userId);

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
	 * delete user from the database.
	 *
	 * @param user
	 *            the user
	 */
	void deleteUser(User user);

	/**
	 * delete user by user Id.
	 *
	 * @param userId
	 *            the user id
	 * @return the int
	 */
	int deleteUserById(long userId);
}
