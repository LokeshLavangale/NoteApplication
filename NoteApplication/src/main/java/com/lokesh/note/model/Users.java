/**
 * 
 */
/*
 * Copyright (c) 2016
 * Lokesh Lavangale All Rights Reserved 
 * This is Demonstration Code only is provided AS IS IN basis without any support 
 * 
 * Use of this code is only for demonstration only and can be re-used with prior permission
 * 
 */
package com.lokesh.note.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lokeshl
 *
 */
@XmlRootElement(name = "users")
public class Users {
	private List<User> users;

	/**
	 * @return the user
	 */

	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	@XmlElement(name = "user")
	public void setUsers(List<User> user) {
		this.users = user;
	}

}
