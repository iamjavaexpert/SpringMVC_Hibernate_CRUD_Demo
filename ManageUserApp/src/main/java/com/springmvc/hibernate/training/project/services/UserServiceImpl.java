package com.springmvc.hibernate.training.project.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.hibernate.training.project.dao.UserDAO;
import com.springmvc.hibernate.training.project.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

	/** The user dao. */
	@Autowired
	private UserDAO userDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserService#addUser(com
	 * .springmvc.hibernate.training.project.model.User)
	 */
	@Override
	@Transactional
	public void addUser(User user) {
		log.info("Executing addUser method");

		user.setCreateDate(LocalDateTime.now());
		user.setModifiedDate(LocalDateTime.now());
		userDao.add(user);

		log.info("User added successfully." + user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserService#updateUser(
	 * com.springmvc.hibernate.training.project.model.User)
	 */
	@Override
	@Transactional
	public void updateUser(User user) {
		log.info("Executing updateUser method");

		user.setModifiedDate(LocalDateTime.now());
		userDao.update(user);

		log.info("User updated successfully." + user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springmvc.hibernate.training.project.services.UserService#
	 * updatePassword(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public int updatePassword(String emailAddress, String password) {
		log.info("Executing updatePassword method");
		return userDao.updatePassword(emailAddress, password);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserService#getUsers()
	 */
	@Override
	@Transactional
	public List<User> getUsers() {
		log.info("Executing getUsers method");
		return userDao.list();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springmvc.hibernate.training.project.services.UserService#
	 * findUserById(long)
	 */
	@Override
	@Transactional
	public User findUserById(long userId) {
		log.info("Executing findUserById method");
		return userDao.findById(userId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserService#deleteUser(
	 * com.springmvc.hibernate.training.project.model.User)
	 */
	@Override
	@Transactional
	public void deleteUser(User user) {
		log.info("Executing deleteUser method");
		userDao.remove(user);
		log.info("User deleted successfully." + user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springmvc.hibernate.training.project.services.UserService#
	 * deleteUserById(long)
	 */
	@Override
	@Transactional
	public int deleteUserById(long userId) {
		log.info("Executing deleteUserById method");
		return userDao.removeById(userId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springmvc.hibernate.training.project.services.UserService#
	 * findUserByUsernamePassword(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public User findUserByUsernamePassword(String username, String password) {
		log.info("userService : Executing findUserByUsernamePassword method");
		return userDao.findUserByUsernamePassword(username, password);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springmvc.hibernate.training.project.services.UserService#
	 * findUserByEmailAddress(java.lang.String)
	 */
	@Override
	@Transactional
	public User findUserByEmailAddress(String emailAddress) {
		log.info("userService : Executing findUserByEmailAddress method");
		return userDao.findUserByEmailAddress(emailAddress);
	}
}
