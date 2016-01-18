/*
 * Copyright (c) 2016
 * Lokesh Lavangale All Rights Reserved 
 * This is Demonstration Code only is provided AS IS IN basis without any support 
 * 
 * Use of this code is only for demonstration only and can be re-used with prior permission
 * 
 */
package com.lokesh.note.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Util class
 * 
 * @author Lokesh Lavangale
 *
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * 
	 */
	public HibernateUtil() {
	}

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		} catch (Throwable ex) {

			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
