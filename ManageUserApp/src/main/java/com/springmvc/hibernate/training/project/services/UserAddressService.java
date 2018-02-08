package com.springmvc.hibernate.training.project.services;

import java.util.List;

import com.springmvc.hibernate.training.project.model.UserAddress;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserAddressService.
 */
public interface UserAddressService {

	/**
	 * add user address into the database.
	 *
	 * @param userAddress
	 *            the user address
	 */
	void addUserAddress(UserAddress userAddress);

	/**
	 * update user address details.
	 *
	 * @param userAddress
	 *            the user address
	 */
	void updateUserAddress(UserAddress userAddress);

	/**
	 * get list of all user addresses.
	 *
	 * @return the user addresses
	 */
	List<UserAddress> getUserAddresses();

	/**
	 * find user address by user address Id.
	 *
	 * @param userAddressId
	 *            the user address id
	 * @return the user address
	 */
	UserAddress findUserAddressById(long userAddressId);

	/**
	 * delete user address from the database.
	 *
	 * @param userAddress
	 *            the user address
	 */
	void deleteUserAddress(UserAddress userAddress);

	/**
	 * delete user address by user address Id.
	 *
	 * @param userAddressId
	 *            the user address id
	 * @return the int
	 */
	int deleteUserAddressById(long userAddressId);
}
