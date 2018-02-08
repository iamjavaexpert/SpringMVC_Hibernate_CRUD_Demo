package com.springmvc.hibernate.training.project.dao;

import org.springframework.stereotype.Repository;

import com.springmvc.hibernate.training.project.model.UserAddress;

/**
 * The Class UserAddressDAOImpl.
 */
@Repository("userAddressDao")
public class UserAddressDAOImpl extends GenericDAOImpl<UserAddress> implements UserAddressDAO {

}
