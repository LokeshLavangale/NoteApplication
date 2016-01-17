/**
 * 
 */
package com.lokesh.note.controller;

import java.util.Collection;

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

	public User findUserById(long id) throws Exception;

	public Collection<User> findAllUsers() throws Exception;

	public boolean createNote(Note note) throws Exception;

	public boolean updateNote(Note note) throws Exception;

	public boolean deleteNote(Note note) throws Exception;

	public Note findNoteById(long id) throws Exception;

	public Collection<Note> findAllNotes() throws Exception;
}
