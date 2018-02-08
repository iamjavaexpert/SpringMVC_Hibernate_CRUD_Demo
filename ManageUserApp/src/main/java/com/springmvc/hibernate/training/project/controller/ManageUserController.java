package com.springmvc.hibernate.training.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.hibernate.training.project.model.User;
import com.springmvc.hibernate.training.project.services.UserAddressService;
import com.springmvc.hibernate.training.project.services.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class ManageUserController.
 */
@Controller
public class ManageUserController {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(ManageUserController.class.getName());

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The user address service. */
	@Autowired
	private UserAddressService userAddressService;

	/** The message resource. */
	@Autowired
	private MessageSource messageResource;

	/**
	 * handle request to navigate to login page.
	 *
	 * @param view
	 *            the view
	 * @param request
	 *            the request
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/", "/userLogin" })
	public ModelAndView load(ModelAndView view, HttpServletRequest request) {
		log.info("Executing /userLogin - GET");

		// if session is still active - redirect to home(welcome page)
		if (request.getSession(false) != null && request.getSession(false).getAttribute("currentUser") != null) {
			view.setViewName("redirect:/home");
			return view;
		}

		// add page title
		view.addObject("pageTitle", "Welcome");

		// set view name to navigate
		view.setViewName("index");

		log.info("Executed /userLogin - GET");
		return view;
	}

	/**
	 * handle request to navigate to user register / update info page.
	 *
	 * @param view
	 *            the view
	 * @param userId
	 *            the user id
	 * @param edit
	 *            the edit
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/registerOrUpdate")
	public ModelAndView registerUser(ModelAndView view, @RequestParam("userId") long userId,
			@RequestParam("editUser") boolean edit) {
		log.info("Executing /registerOrUpdate - GET");

		String pageTitle = "Register";

		// add object of user into the attribute
		if (edit) {

			User user = null;
			try {
				// get user details to edit
				user = userService.findUserById(userId);
			} catch (Exception e) {
				log.error("Exception while getting user by id: " + userId, e);

				view.addObject("errorMessage",
						getMessageFromProperty("unexpected-error", null, "Something went wrong. Try again!"));
				view.setViewName("redirect:/home");
				return view;
			}

			// if no user found, redirect to welcome page
			if (user == null) {

				view.addObject("errorMessage", getMessageFromProperty("no-user-found", null, "No user found!"));
				view.setViewName("redirect:/home");
				return view;
			}

			// add user form as attribute with user details to display on the
			// page to update
			view.addObject("userForm", user);
			pageTitle = "Edit User";
		} else {
			// add blank user object for create
			view.addObject("userForm", new User());
		}

		// add page title
		view.addObject("pageTitle", pageTitle);

		// set view name to navigate
		view.setViewName("registerOrUpdate");

		log.info("Executed /registerOrUpdate - GET");
		return view;
	}

	/**
	 * handle request to create / update user.
	 *
	 * @param view
	 *            the view
	 * @param user
	 *            the user
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/registerOrUpdate")
	public ModelAndView registerUser(ModelAndView view, @ModelAttribute("userForm") User user, BindingResult result) {
		log.info("Executing /registerOrUpdate - POST");

		if (user == null) {
			log.error("user modelAttribute is null.");

			view.addObject("errorMessage", getMessageFromProperty("no-user-found", null, "No user found!"));
			view.setViewName("redirect:/userLogin");
			return view;
		}

		// check for srvice related errors
		if (result.hasErrors()) {
			log.error("service level error/exception.");
			result.getAllErrors().forEach(action -> {
				log.error(action.getDefaultMessage());
			});

			// if any exception generated while execution of service
			view.addObject("errorMessage",
					getMessageFromProperty("unexpected-error", null, "Something went wrong. Try again!"));
			if (user.getId() > 0) {
				view.setViewName("redirect:/home");
			} else {
				view.setViewName("redirect:/userLogin");
			}

			return view;
		}

		// add / update user details and display user list based on the role
		if (user.getId() > 0) {
			log.info("Updating user details");

			// update user
			userService.updateUser(user);
			view.addObject("successMessage",
					getMessageFromProperty("user-updated-successfully", null, "User details updated successfully."));

			// set view name to navigate
			view.setViewName("redirect:/home");
		} else {
			log.info("Adding user.");

			// validate by email address - if can not register by existing email
			// address
			if (userService.findUserByEmailAddress(user.getEmailAddress()) == null) {

				// add user into the DB and redirect to login page for user
				// login with success message
				userService.addUser(user);
				view.addObject("successMessage",
						getMessageFromProperty("user-registered-successfully", null, "User registered successfully."));
				view.setViewName("redirect:/userLogin");
			} else {

				// if user with entered email address exists the provide error
				// messsage

				view.addObject("errorMessage",
						getMessageFromProperty("email-address-duplication-error",
								new String[] { user.getEmailAddress() },
								"User with same email address - " + user.getEmailAddress() + " already exists."));
				view.setViewName("redirect:/registerOrUpdate?userId=0&editUser=false");
			}
		}

		log.info("Executed /registerOrUpdate - POST");
		return view;
	}

	/**
	 * handle request to authenticate user and navigate to welcome page.
	 *
	 * @param view
	 *            the view
	 * @param request
	 *            the request
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/userLogin")
	public ModelAndView loginUser(ModelAndView view, HttpServletRequest request) {
		log.info("Executing /userLogin - POST");

		// get username and password
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = null;

		try {
			// validate if user has been registered
			user = userService.findUserByUsernamePassword(username, password);
		} catch (Exception e) {
			log.error("Exception while getting user by username: " + username + " and password: " + password, e);

			view.addObject("errorMessage",
					getMessageFromProperty("unexpected-error", null, "Something went wrong. Try again!"));
			view.setViewName("redirect:/userLogin");
			return view;
		}

		if (user == null) {
			log.info("No user found with provided credentials to login.");

			// provided error message if user is not registered
			view.addObject("errorMessage",
					getMessageFromProperty("incorrect-username-password-error", null, "Username/Password Incorrect!"));
			view.setViewName("redirect:/userLogin");
			return view;
		}

		// if user exists, create new session
		HttpSession userSession = request.getSession();

		// add user object into the session
		userSession.setAttribute("currentUser", user);

		// set view name to navigate
		view.setViewName("redirect:/home");

		log.info("Executed /userLogin - POST");
		return view;
	}

	/**
	 * handle request to delete user.
	 *
	 * @param view
	 *            the view
	 * @param result
	 *            the result
	 * @param userId
	 *            the user id
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/deleteUser")
	public ModelAndView deleteUser(ModelAndView view, BindingResult result, @RequestParam("userId") long userId) {
		log.info("Executing /deleteUser - GET");

		// manage result if has errors while delete user
		if (result.hasErrors()) {
			log.error("service level error/exception.");
			result.getAllErrors().forEach(action -> {
				log.error(action.getDefaultMessage());
			});

			view.addObject("errorMessage",
					getMessageFromProperty("unable-user-delete", null, "Unable to delete user. Try again!"));
			view.setViewName("redirect:/home");
			return view;
		}

		log.info("deleting user " + userId);
		// delete user and provide error/success meesage
		if (userService.deleteUserById(userId) == 0) {
			view.addObject("errorMessage",
					getMessageFromProperty("unable-user-delete", null, "Unable to delete user. Try again!"));
		} else {
			view.addObject("successMessage",
					getMessageFromProperty("user-deleted-successfully", null, "User deleted successfully."));
		}

		// set view name to navigate and get all users list and display on the
		// page
		view.setViewName("redirect:/home");

		log.info("Executed /deleteUser - GET");
		return view;
	}

	/**
	 * handle request to delete user address while register / edit user.
	 *
	 * @param view
	 *            the view
	 * @param result
	 *            the result
	 * @param userAddressId
	 *            the user address id
	 * @param userId
	 *            the user id
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/deleteUserAddress")
	public ModelAndView deleteUserAddress(ModelAndView view, BindingResult result,
			@RequestParam("userAddressId") long userAddressId, @RequestParam("userId") long userId) {
		log.info("Executing /deleteUserAddress - GET");

		// check for srvice related errors
		if (result.hasErrors()) {
			log.error("service level error/exception.");
			result.getAllErrors().forEach(action -> {
				log.error(action.getDefaultMessage());
			});

			view.addObject("errorMessage", getMessageFromProperty("unable-user-address-delete", null,
					"Unable to delete user address. Try again!"));
			view.setViewName("redirect:/home");
			return view;
		}

		log.info("deleting address " + userAddressId + "of user " + userId);

		// delete user address and provide error / success message
		if (userAddressService.deleteUserAddressById(userAddressId) > 0) {
			view.addObject("successMessage",
					getMessageFromProperty("user-address-deleted-successfully", null, "Address deleted successfully."));
		} else {
			view.addObject("errorMessage", getMessageFromProperty("unable-user-address-delete", null,
					"Unable to delete user address. Try again!"));
		}

		// redirect to registration page for update user details
		view.addObject("userId", userId);
		view.addObject("editUser", true);
		view.setViewName("redirect:/registerOrUpdate");

		log.info("Executed /deleteUserAddress - GET");
		return view;
	}

	/**
	 * handle request to view user detail information.
	 *
	 * @param view
	 *            the view
	 * @param result
	 *            the result
	 * @param userId
	 *            the user id
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/viewUserDetails")
	public ModelAndView viewUser(ModelAndView view, BindingResult result, @RequestParam("userId") long userId) {
		log.info("Executing /viewUserDetails - GET");

		// check for srvice related errors
		if (result.hasErrors()) {
			log.error("service level error/exception.");
			result.getAllErrors().forEach(action -> {
				log.error(action.getDefaultMessage());
			});

			view.addObject("errorMessage",
					getMessageFromProperty("unexpected-error", null, "Something went wrong. Try again!"));
			view.setViewName("redirect:/home");
		} else {
			// get user details by user id and display on the page
			view.addObject("user", userService.findUserById(userId));
			view.addObject("pageTitle", "User Details");
			view.setViewName("viewUser");
		}

		log.info("Executed /viewUserDetails - GET");
		return view;
	}

	/**
	 * handle request to navigate to reset password form page.
	 *
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/forgotPassword")
	public ModelAndView forgotPassword() {
		log.info("Executing /forgotPassword - GET");

		// navigate to reset password page
		ModelAndView view = new ModelAndView();
		view.addObject("pageTitle", "Reset Password");
		view.setViewName("resetPassword");

		log.info("Executed /forgotPassword - GET");
		return view;
	}

	/**
	 * handle request to reset password and login again.
	 *
	 * @param request
	 *            the request
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/forgotPassword")
	public ModelAndView resetPassword(HttpServletRequest request) {
		log.info("Executing /forgotPassword - POST");

		// get entered email address and new password to reset
		String emailAddress = request.getParameter("emailAddress");
		String newPassword = request.getParameter("password");

		// update password by email address and display error/success message
		// on the login / reset password page
		ModelAndView view = new ModelAndView();
		try {
			if (userService.updatePassword(emailAddress, newPassword) > 0) {
				view.addObject("successMessage",
						getMessageFromProperty("pasword-updated-successfully", null, "Password updated successfully"));
				view.setViewName("redirect:/userLogin");
			} else {

				view.addObject("errorMessage", getMessageFromProperty("no-user-with-x-email-address-found",
						new String[] { emailAddress }, "User with email address " + emailAddress + " not found!"));
				view.setViewName("redirect:/forgotPassword");
			}
		} catch (Exception e) {
			log.error("Exception while update password: " + newPassword + " by email address: " + emailAddress, e);
			view.addObject("errorMessage",
					getMessageFromProperty("unexpected-error", null, "Something went wrong. Try again!"));
			view.setViewName("redirect:/forgotPassword");
		}

		log.info("Executed /forgotPassword - POST");
		return view;
	}

	/**
	 * handle request to invalidate session logout from the user account.
	 *
	 * @param request
	 *            the request
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		log.info("Executing /logout - GET");

		// get current active session and invalidate
		HttpSession userSession = request.getSession(false);
		if (userSession != null && userSession.getAttribute("currentUser") != null) {
			log.info("invalidating current active session");
			userSession.invalidate();
		}

		// redirect to login page again
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/userLogin");

		log.info("Executed /logout - GET");
		return view;
	}

	/**
	 * handle request to navigate to home (welcome) page.
	 *
	 * @param view
	 *            the view
	 * @param request
	 *            the request
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public ModelAndView goToHomePage(ModelAndView view, HttpServletRequest request, BindingResult result) {
		log.info("Executing /home - GET");

		// check for srvice related errors
		if (result.hasErrors()) {
			log.error("service level error/exception.");
			result.getAllErrors().forEach(action -> {
				log.error(action.getDefaultMessage());
			});

			view.addObject("errorMessage",
					getMessageFromProperty2("unexpected-error", null, "Something went wrong. Try again!"));
			view.setViewName("redirect:/userLogin");
			return view;
		}

		log.info("Preparing user list to display on welcome page");
		// get current user object form the active session
		User currentUser = (User) request.getSession(false).getAttribute("currentUser");

		// if user is administrator - display all users
		// else display loggedin user only into the user list
		List<User> userList = new ArrayList<User>();
		if ("User".equals(currentUser.getRole())) {
			userList.add(currentUser);
		} else {
			userList.addAll(userService.getUsers());
		}

		// add page title
		view.addObject("pageTitle", "Welcome");

		// add userList as attribute to didplay on the page
		view.addObject("userList", userList);

		// set view name to navigate
		view.setViewName("welcome");

		log.info("Executed /home - GET");
		return view;
	}

	private String getMessageFromProperty(String propertyKey, Object[] params, String defaultPropertyValue) {
		String message = messageResource.getMessage(propertyKey, params, defaultPropertyValue, Locale.getDefault());
		if (message == null) {
			message = defaultPropertyValue;
		}

		return message;
	}

	private String getMessageFromProperty2(String propertyKey, Object[] params, String defaultPropertyValue) {
		String message = messageResource.getMessage(propertyKey, params, defaultPropertyValue, Locale.getDefault());
		if (message == null) {
			message = defaultPropertyValue;
		}

		return message;
	}
}