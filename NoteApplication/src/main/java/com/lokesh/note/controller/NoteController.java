/**
 * 
 */
package com.lokesh.note.controller;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.lokesh.note.model.Note;
import com.lokesh.note.model.User;
import com.lokesh.note.util.HibernateUtil;

/**
 * @author Lokesh Lavangale
 *
 */
public class NoteController implements NoteControllerInterface {

	private Session session;

	/**
	 * 
	 */
	public NoteController() {
		openSession();
	}

	private void openSession() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lokesh.note.controller.NoteControllerInterface#createUser(com.lokesh.
	 * note.model.User)
	 */
	@Override
	public boolean createUser(User user) throws Exception {
		boolean result = false;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				Transaction t = session.beginTransaction();
				session.save(user);
				t.commit();
				session.close();
				result = true;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lokesh.note.controller.NoteControllerInterface#updateUser(com.lokesh.
	 * note.model.User)
	 */
	@Override
	public boolean updateUser(User user) throws Exception {
		boolean result = false;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				Transaction t = session.beginTransaction();
				session.saveOrUpdate(user);
				t.commit();
				session.close();
				result = true;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lokesh.note.controller.NoteControllerInterface#deleteUser(com.lokesh.
	 * note.model.User)
	 */
	@Override
	public boolean deleteUser(User user) throws Exception {
		boolean result = false;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				Transaction t = session.beginTransaction();
				session.delete(user);
				t.commit();
				session.close();
				result = true;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lokesh.note.controller.NoteControllerInterface#findUserById(long)
	 */
	@Override
	public User findUserById(long id) throws Exception {
		User user = null;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				Criteria crt = session.createCriteria(User.class);
				crt.add(Restrictions.eq("id", id));
				user = (User) crt.uniqueResult();
				session.close();
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}

		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lokesh.note.controller.NoteControllerInterface#findAllUsers()
	 */
	@Override
	public Collection<User> findAllUsers() throws Exception {
		List<User> users = null;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				users = session.createCriteria(User.class).list();
				session.close();
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}

		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lokesh.note.controller.NoteControllerInterface#createNote(com.lokesh.
	 * note.model.Note)
	 */
	@Override
	public boolean createNote(Note note) throws Exception {
		boolean result = false;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				Transaction t = session.beginTransaction();
				session.save(note);
				t.commit();
				session.close();
				result = true;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lokesh.note.controller.NoteControllerInterface#updateNote(com.lokesh.
	 * note.model.Note)
	 */
	@Override
	public boolean updateNote(Note note) throws Exception {
		boolean result = false;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				Transaction t = session.beginTransaction();
				session.saveOrUpdate(note);
				t.commit();
				session.close();
				result = true;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lokesh.note.controller.NoteControllerInterface#deleteNote(com.lokesh.
	 * note.model.Note)
	 */
	@Override
	public boolean deleteNote(Note note) throws Exception {
		boolean result = false;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				Transaction t = session.beginTransaction();
				session.delete(note);
				t.commit();
				session.close();
				result = true;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lokesh.note.controller.NoteControllerInterface#findNoteById(long)
	 */
	@Override
	public Note findNoteById(long id) throws Exception {
		Note note = null;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				Criteria crt = session.createCriteria(Note.class);
				crt.add(Restrictions.eq("id", id));
				note = (Note) crt.uniqueResult();
				session.close();
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}

		return note;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lokesh.note.controller.NoteControllerInterface#findAllNotes()
	 */
	@Override
	public Collection<Note> findAllNotes() throws Exception {
		List<Note> notes = null;
		if (session != null) {
			if (!session.isOpen()) {
				openSession();
			}
			try {
				notes = session.createCriteria(Note.class).list();
				session.close();
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Hibernate session does not exist");
		}

		return notes;
	}

}