/*
 * Copyright (c) 2016
 * Lokesh Lavangale All Rights Reserved 
 * This is Demonstration Code only is provided AS IS IN basis without any support 
 * 
 * Use of this code is only for demonstration only and can be re-used with prior permission
 * 
 */
package com.lokesh.note.service.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

import com.lokesh.note.controller.NoteController;
import com.lokesh.note.model.User;

/**
 * @author Lokesh Lavangale
 * 
 *         This interceptor verify the access permissions for a user based on
 *         username and password provided in request
 */
@Provider
public class SecurityInterceptor implements javax.ws.rs.container.ContainerRequestFilter {
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401,
			new Headers<Object>());;
	private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403,
			new Headers<Object>());;
	private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500,
			new Headers<Object>());;

	@Override
	public void filter(ContainerRequestContext requestContext) {
		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext
				.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
		Method method = methodInvoker.getMethod();

		// Access denied for all
		if (method.isAnnotationPresent(DenyAll.class)) {
			requestContext.abortWith(ACCESS_FORBIDDEN);
			return;
		}

		if (method.isAnnotationPresent(PermitAll.class)) {
			// Get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();

			// Fetch authorization header
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			// If no authorization information present; block access
			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(ACCESS_DENIED);
				return;
			}

			// Get encoded username and password
			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

			// Decode username and password
			String usernameAndPassword = null;
			try {
				usernameAndPassword = new String(Base64.decode(encodedUserPassword));
			} catch (IOException e) {
				requestContext.abortWith(SERVER_ERROR);
				return;
			}

			// Split username and password tokens
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();

			String path = requestContext.getUriInfo().getPath();
			String temp[] = path.replaceFirst("/", "").split("/");

			// check for path is till notes
			if (temp.length >= 3) {
				// check path contains notes
				if (temp[3].equals("notes")) {
					String userId = "0";
					// get user id from path
					userId = temp[2].trim();
					// Verify user access
					if (!isUserAllowedForNotes(username, password, userId)) {
						requestContext.abortWith(ACCESS_DENIED);
						return;
					}
				}
			}
		}
	}

	private boolean isUserAllowedForNotes(final String username, final String password, String userId) {
		boolean isAllowed = false;

		NoteController controller = new NoteController();
		try {
			User user = controller.findUserByUserName(username);
			if (user != null && user.getPassword().equals(password)) {
				if (user.getId() == Long.parseLong(userId)) {
					isAllowed = true;
				}
			}
		} catch (Exception e) {
			return isAllowed;
		}

		return isAllowed;
	}
}