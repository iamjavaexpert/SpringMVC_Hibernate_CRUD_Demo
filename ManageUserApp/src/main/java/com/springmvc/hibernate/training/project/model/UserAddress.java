package com.springmvc.hibernate.training.project.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class UserAddress.
 */
@Entity
@Table
public class UserAddress implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private long id;

	/** The street 1. */
	private String street1;

	/** The street 2. */
	private String street2;

	/** The landmark. */
	private String landmark;

	/** The city. */
	private String city;

	/** The state. */
	private String state;

	/** The country. */
	private String country;

	/** The pincode. */
	private long pincode;

	/** The primary address. */
	private boolean primaryAddress;

	/** The create date. */
	private LocalDateTime createDate;

	/** The modified date. */
	private LocalDateTime modifiedDate;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@Column(name = "userAddressId", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_address_sequence")
	@SequenceGenerator(name = "user_address_sequence", sequenceName = "user_address_seq", allocationSize = 1)
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the street 1.
	 *
	 * @return the street 1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * Sets the street 1.
	 *
	 * @param street1
	 *            the new street 1
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * Gets the street 2.
	 *
	 * @return the street 2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * Sets the street 2.
	 *
	 * @param street2
	 *            the new street 2
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * Gets the landmark.
	 *
	 * @return the landmark
	 */
	public String getLandmark() {
		return landmark;
	}

	/**
	 * Sets the landmark.
	 *
	 * @param landmark
	 *            the new landmark
	 */
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the pincode.
	 *
	 * @return the pincode
	 */
	public long getPincode() {
		return pincode;
	}

	/**
	 * Sets the pincode.
	 *
	 * @param pincode
	 *            the new pincode
	 */
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	/**
	 * Gets the primary address.
	 *
	 * @return the primary address
	 */
	public boolean getPrimaryAddress() {
		return primaryAddress;
	}

	/**
	 * Sets the primary address.
	 *
	 * @param primaryAddress
	 *            the new primary address
	 */
	public void setPrimaryAddress(boolean primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate
	 *            the new creates the date
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate
	 *            the new modified date
	 */
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
