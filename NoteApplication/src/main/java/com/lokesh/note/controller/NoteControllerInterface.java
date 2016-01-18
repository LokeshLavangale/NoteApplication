/**
 * 
 */
package com.lokesh.note.controller;

import java.util.List;

import com.lokesh.note.model.Note;
import com.lokesh.note.model.User;

/**
 * @author Lokesh Lavangale
 *
 */
public interface NoteControllerInterface {

	public boolean createUser(User user) throws Exception;

	public boolean updateUser(User user) throws Exception;

	public boolean deleteUser(User user) throws Exception;
	
	public boolean deleteUser(long userId) throws Exception;

	public User findUserById(long id) throws Exception;
	
	public User findUserByUserName(String userName) throws Exception;

	public List<User> findAllUsers() throws Exception;

	public boolean createNote(Note note) throws Exception;

	public boolean updateNote(Note note) throws Exception;

	public boolean deleteNote(Note note) throws Exception;
	
	public boolean deleteNote(long id) throws Exception;

	public Note findNoteById(long id) throws Exception;

	public List<Note> findAllNotes() throws Exception;
}
