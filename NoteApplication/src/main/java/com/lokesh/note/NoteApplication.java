/**
 * 
 */
package com.lokesh.note;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.lokesh.note.controller.NoteController;
import com.lokesh.note.model.Note;
import com.lokesh.note.model.Notes;
import com.lokesh.note.model.User;
import com.lokesh.note.model.Users;

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
		 //app.addUser();
		// app.addUser();
		 //app.addUser();
		 //app.addUser();
		 //app.addUser();
	//	 app.findAllUsers();
		//   app.updateUser();
		//app.deleteUser();

		// app.addNote();
		// app.findAllNotes();
		// app.upadateNote();
		// app.deleteNote();

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
		user.setEmail("TestUser1@test.com");
		user.setPassword("password");
		user.setNotes(notes);
		user.setCreationTime(new Date());
		user.setLastModified(new Date());

		new NoteController().createUser(user);

	}

	private List<User> findAllUsers() throws Exception {
		List<User> users =  new NoteController().findAllUsers();
		
		Users parentUsers = new Users();
		parentUsers.setUsers(users);
		Marshaller jaxbMarshaller = JAXBContext.newInstance(Users.class).createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(parentUsers, sw);
		
		System.out.println(sw.toString());
		
		for (User user : users) {
			System.out.println("User ID" + user.getId() + " :: " + user.getEmail());

/*			StringWriter sw = new StringWriter();
					
			Marshaller jaxbMarshaller = JAXBContext.newInstance(User.class).createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(user, sw);
			
			System.out.println(sw.toString());
			break;*/
		}
		return users;
	}

	private void updateUser() throws Exception {
		List<User> users = findAllUsers();
		for (User user : users) {
			user.setEmail(user.getEmail() + 1);
			user.getNotes().getNote().remove(0);
			new NoteController().updateUser(user);
		}
	}

	private void deleteUser() throws Exception {
		List<User> users = findAllUsers();
		for (User user : users) {
			new NoteController().deleteUser(user);
			//break;
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
