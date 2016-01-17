/**
 * 
 */
package com.lokesh.note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lokesh.note.controller.NoteController;
import com.lokesh.note.model.Note;
import com.lokesh.note.model.Notes;
import com.lokesh.note.model.User;
import com.lokesh.note.util.HibernateUtil;

/**
 * @author Lokesh Lavangale
 *
 *         Class to Test functionality
 */
public class NoteApplication {

	/**
	 * 
	 */
	public NoteApplication() {

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Note Application");

		NoteApplication app = new NoteApplication();

		app.addUser();
		app.findAllUsers();
		app.updateUser();
		app.deleteUser();
		
		app.addNote();
		app.findAllNotes();
		app.upadateNote();
		//app.deleteNote();
		
		System.out.println("Note successfully saved");
	}

	private void addUser() throws Exception {
		Note note = new Note();
		note.setTitle("Test");
		note.setNote("This is a test note");
		note.setCreationTime(new Date());
		note.setLastModified(new Date());

		Notes notes = new Notes();
		List<Note> note1 = new ArrayList<Note>();
		note1.add(note);
		notes.setNote(note1);

		User user = new User();
		user.setName("TestUser");
		user.setPassword("password");
		user.setNotes(notes);
		user.setCreationTime(new Date());
		user.setLastModified(new Date());

		new NoteController().createUser(user);

	}

	private List<User> findAllUsers() throws Exception {
		List<User> users = (List<User>) new NoteController().findAllUsers();
		for (User user : users) {
			System.out.println("User ID" + user.getId() + " :: " + user.getName());
		}
		return users;
	}

	private void updateUser() throws Exception {
		List<User> users = findAllUsers();
		for (User user : users) {
			user.setName(user.getName() + 1);
			new NoteController().updateUser(user);
		}
	}

	private void deleteUser() throws Exception {
		List<User> users = findAllUsers();
		for (User user : users) {
			new NoteController().deleteUser(user);
			break;
		}
	}

	private void addNote() throws Exception {
		Note note = new Note();
		note.setTitle("Test");
		note.setNote("This is a test note");
		note.setCreationTime(new Date());
		note.setLastModified(new Date());

		new NoteController().createNote(note);
	}

	private List<Note> findAllNotes() throws Exception {
		List<Note> notes = (List<Note>) new NoteController().findAllNotes();
		for (Note note : notes) {
			System.out.println(" Note : " + note.getId() + " :: " + note.getTitle());
		}
		return notes;
	}

	private void upadateNote() throws Exception {
		List<Note> notes = findAllNotes();
		for (Note note : notes) {
			note.setTitle(note.getTitle() + "1213");
			new NoteController().updateNote(note);
		}
	}

	private void deleteNote() throws Exception {
		List<Note> notes = findAllNotes();
		for (Note note : notes) {
			new NoteController().deleteNote(note);
			break;
		}
	}
}
