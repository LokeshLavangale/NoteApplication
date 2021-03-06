/*
 * Copyright (c) 2016
 * Lokesh Lavangale All Rights Reserved 
 * This is Demonstration Code only is provided AS IS IN basis without any support 
 * 
 * Use of this code is only for demonstration only and can be re-used with prior permission
 * 
 */
package com.lokesh.note.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.Email;

import com.lokesh.note.util.DesLib;

/**
 * @author Lokesh Lavangale
 *
 */
@Entity
@Table(name = "user")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "user")
@XmlType(propOrder = { "id", "email", "password", "notes", "creationTime", "lastModified" })
@XmlRootElement(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private byte[] pwd;

	private String password;

	private String email;

	private Date creationTime;

	private Date lastModified;

	private Notes notes;

	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@XmlElement
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the pwd
	 */
	@Column(name = "password")
	@XmlTransient
	public byte[] getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(byte[] pwd) {
		this.pwd = pwd;
	}

	@Transient
	@Column(nullable = false)
	@Size(min = 8)
	public String getPassword() {
		String result = null;
		try {
			result = DesLib.decrypt(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transient
	@XmlElement
	public void setPassword(String password) {
		try {
			this.pwd = DesLib.encrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the name
	 */
	@Column(unique = true, nullable = false)
	@Email
	public String getEmail() {
		return email;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime
	 *            the creationTime to set
	 */
	@XmlElement
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the lastModified
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified
	 *            the lastModified to set
	 */
	@XmlElement
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * @return the notes
	 */
	@OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Notes getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	@XmlElement
	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastModified == null) ? 0 : lastModified.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + Arrays.hashCode(pwd);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (creationTime == null) {
			if (other.creationTime != null) {
				return false;
			}
		} else if (!creationTime.equals(other.creationTime)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (lastModified == null) {
			if (other.lastModified != null) {
				return false;
			}
		} else if (!lastModified.equals(other.lastModified)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (notes == null) {
			if (other.notes != null) {
				return false;
			}
		} else if (!notes.equals(other.notes)) {
			return false;
		}
		if (!Arrays.equals(pwd, other.pwd)) {
			return false;
		}
		return true;
	}

}
