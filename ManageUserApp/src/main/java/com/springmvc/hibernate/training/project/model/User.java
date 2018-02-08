package com.springmvc.hibernate.training.project.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@NamedQueries({
		@NamedQuery(name = "findUserByUsernamePassword", query = "from User u where u.userName=? and u.password=?"),
		@NamedQuery(name = "findUserByEmailAddress", query = "from User u where u.emailAddress=?") })

@Entity
@Table
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private long id;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The gender. */
	private String gender;

	/** The role. */
	private String role;

	/** The user name. */
	private String userName;

	/** The password. */
	private String password;

	/** The email address. */
	private String emailAddress;

	/** The phone. */
	private long phone;

	/** The create date. */
	private LocalDateTime createDate;

	/** The modified date. */
	private LocalDateTime modifiedDate;

	/** The user addresses. */
	private List<UserAddress> userAddresses;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@Column(name = "userId", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
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
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender
	 *            the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role
	 *            the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress
	 *            the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public long getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(long phone) {
		this.phone = phone;
	}

	// TODO Change to lazy loading and refer
	// link:https://howtodoinjava.com/hibernate/use-hibernate-initialize-to-initialize-proxycollection/
	// to solve exception: org.hibernate.LazyInitializationException:
	// failed to lazily initialize a collection of role
	/**
	 * Gets the user addresses.
	 *
	 * @return the user addresses
	 */
	@OneToMany(fetch = FetchType.EAGER, targetEntity = UserAddress.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	public List<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	/**
	 * Sets the user addresses.
	 *
	 * @param userAddresses
	 *            the new user addresses
	 */
	public void setUserAddresses(List<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
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
