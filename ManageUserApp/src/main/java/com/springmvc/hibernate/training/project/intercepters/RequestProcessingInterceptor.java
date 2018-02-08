package com.springmvc.hibernate.training.project.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestProcessingInterceptor.
 */
public class RequestProcessingInterceptor implements HandlerInterceptor {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(RequestProcessingInterceptor.class.getName());

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// get request mapping annotation details
		RequestMapping requestMapping = ((HandlerMethod) handler).getMethodAnnotation(RequestMapping.class);

		// get current active status and attribute
		// isSignedIn - true : session is expired
		// isSignedIn - false : session is still active
		boolean isSignedIn = request.getSession(false) == null
				|| request.getSession(false).getAttribute("currentUser") == null ? true : false;

		// For register page, if editUser is true the check for status
		if (requestMapping != null && "/registerOrUpdate".equals(requestMapping.path()[0])) {
			log.info("editUser:" + request.getParameter("editUser"));
			isSignedIn = isSignedIn && "true".equals(request.getParameter("editUser"));
		}

		// if true then session has been expired and redirect user to login
		// page
		if (isSignedIn) {
			response.sendRedirect(request.getContextPath() + "/userLogin");
			return false;
		}

		return true;
	}
}
