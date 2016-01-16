/**
 * 
 */
package com.lokesh.note.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Lokesh Lavangale
 *
 */
@Entity
@Table(name = "notes")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "user")
public class Notes implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private Long id;
	
	protected List<Note> note;

	/**
	 * 
	 */
	public Notes() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the note
	 */
	@OneToMany(targetEntity = Note.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Note> getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(List<Note> note) {
		this.note = note;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		if (!(obj instanceof Notes)) {
			return false;
		}
		Notes other = (Notes) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (note == null) {
			if (other.note != null) {
				return false;
			}
		} else if (!note.equals(other.note)) {
			return false;
		}
		return true;
	}

	
}
