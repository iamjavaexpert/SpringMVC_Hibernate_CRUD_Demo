package com.springmvc.hibernate.training.project.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.hibernate.training.project.dao.UserAddressDAO;
import com.springmvc.hibernate.training.project.model.UserAddress;

// TODO: Auto-generated Javadoc
/**
 * The Class UserAddressServiceImpl.
 *
 * @author inexture
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(UserAddressServiceImpl.class.getName());

	/** The user address dao. */
	@Autowired
	private UserAddressDAO userAddressDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserAddressService#
	 * addUserAddress(com.springmvc.hibernate.training.project.model.
	 * UserAddress)
	 */
	@Override
	@Transactional
	public void addUserAddress(UserAddress userAddress) {
		log.info("Executing addUserAddress method");

		userAddress.setCreateDate(LocalDateTime.now());
		userAddress.setModifiedDate(LocalDateTime.now());
		userAddressDao.add(userAddress);

		log.info("User Address added." + userAddress);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserAddressService#
	 * updateUserAddress(com.springmvc.hibernate.training.project.model.
	 * UserAddress)
	 */
	@Override
	@Transactional
	public void updateUserAddress(UserAddress userAddress) {
		log.info("Executing updateUserAddress method");

		userAddress.setModifiedDate(LocalDateTime.now());
		userAddressDao.update(userAddress);

		log.info("User Address updated." + userAddress);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserAddressService#
	 * getUserAddresses()
	 */
	@Override
	@Transactional
	public List<UserAddress> getUserAddresses() {
		log.info("Executing getUserAddresses method");
		return userAddressDao.list();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserAddressService#
	 * findUserAddressById(long)
	 */
	@Override
	@Transactional
	public UserAddress findUserAddressById(long userAddressId) {
		log.info("Executing findUserAddressById method");
		return userAddressDao.findById(userAddressId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserAddressService#
	 * deleteUserAddress(com.springmvc.hibernate.training.project.model.
	 * UserAddress)
	 */
	@Override
	@Transactional
	public void deleteUserAddress(UserAddress userAddress) {
		log.info("Executing deleteUserAddress method");
		userAddressDao.remove(userAddress);
		log.info("User Address deleted." + userAddress);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.springmvc.hibernate.training.project.services.UserAddressService#
	 * deleteUserAddressById(long)
	 */
	@Override
	@Transactional
	public int deleteUserAddressById(long userAddressId) {
		log.info("Executing deleteUserAddressById method");
		return userAddressDao.removeById(userAddressId);
	}
}
