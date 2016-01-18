/*
 * Copyright (c) 2016
 * Lokesh Lavangale All Rights Reserved 
 * This is Demonstration Code only is provided AS IS IN basis without any support 
 * 
 * Use of this code is only for demonstration only and can be re-used with prior permission
 * 
 */
package com.lokesh.note.service;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.lokesh.note.controller.NoteController;
import com.lokesh.note.model.Note;
import com.lokesh.note.model.Notes;
import com.lokesh.note.model.User;
import com.lokesh.note.model.Users;

/**
 * @author Lokesh Lavangale
 *
 */
@Path("/service")
public class NoteService {

	@DenyAll
	@GET
	@Produces("application/xml")
	@Path("/users")
	public Response getAllUsers() {

		StringWriter sw = new StringWriter();
		try {
			Marshaller jaxbMarshaller = JAXBContext.newInstance(Users.class).createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			List<User> users = (List<User>) new NoteController().findAllUsers();
			Users user = new Users();
			user.setUsers(users);
			jaxbMarshaller.marshal(user, sw);
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(sw.toString()).build();
	}

	@DenyAll
	@GET
	@Produces("application/xml")
	@Path("/users/{id}")
	public Response getUser(@PathParam("id") String id) {

		StringWriter sw = new StringWriter();
		try {
			Marshaller jaxbMarshaller = JAXBContext.newInstance(User.class).createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			User user = new NoteController().findUserById(Long.parseLong(id));
			jaxbMarshaller.marshal(user, sw);
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(sw.toString()).build();
	}

	@DenyAll
	@POST
	@Consumes("application/xml")
	@Produces("text/plain")
	@Path("/users")
	public Response addUser(InputStream is) throws JAXBException {
		boolean result = false;
		User user = (User) JAXBContext.newInstance(User.class).createUnmarshaller().unmarshal(is);
		try {
			result = new NoteController().createUser(user);
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(Boolean.toString(result)).build();
	}

	@DenyAll
	@PUT
	@Consumes("application/xml")
	@Produces("text/plain")
	@Path("/users/{id}")
	public Response updateUser(InputStream is) throws JAXBException {
		boolean result = false;
		User user = (User) JAXBContext.newInstance(User.class).createUnmarshaller().unmarshal(is);
		Date date = new Date();
		user.setLastModified(date);
		if (user.getNotes() != null) {
			if (user.getNotes().getNote() != null) {
				for (Note note : user.getNotes().getNote()) {
					note.setLastModified(date);
				}
			}
		}
		try {
			result = new NoteController().updateUser(user);
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(Boolean.toString(result)).build();
	}

	@DenyAll
	@DELETE
	@Path("/users/{id}")
	public Response deleteUser(@PathParam("id") String id) {
		boolean result = false;
		try {
			result = new NoteController().deleteUser(Long.parseLong(id));
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(Boolean.toString(result)).build();
	}

	@PermitAll
	@PUT
	@Consumes("application/xml")
	@Produces("text/plain")
	@Path("/users/{id}/notes/{note_id}")
	public Response updateNote(InputStream is) throws JAXBException {
		boolean result = false;
		Note note = (Note) JAXBContext.newInstance(Note.class).createUnmarshaller().unmarshal(is);
		Date date = new Date();
		note.setLastModified(date);

		try {
			result = new NoteController().updateNote(note);
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(Boolean.toString(result)).build();
	}

	@PermitAll
	@POST
	@Consumes("application/xml")
	@Produces("text/plain")
	@Path("/users/{id}/notes")
	public Response addNote(@PathParam("id") String id, InputStream is) throws JAXBException {
		boolean result = false;
		Note note = (Note) JAXBContext.newInstance(Note.class).createUnmarshaller().unmarshal(is);
		Date date = new Date();
		note.setCreationTime(date);
		note.setLastModified(date);
		try {
			NoteController controller = new NoteController();

			User user = controller.findUserById(Long.parseLong(id));
			if (user.getNotes() == null) {
				Notes notes = new Notes();
				user.setNotes(notes);
			}
			if (user.getNotes().getNote() == null) {
				List<Note> note_1 = new ArrayList<Note>();
				user.getNotes().setNote(note_1);
			}
			user.getNotes().getNote().add(note);
			result = controller.updateUser(user);
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(Boolean.toString(result)).build();
	}

	@PermitAll
	@GET
	@Produces("application/xml")
	@Path("/users/{id}/notes")
	public Response getUserNotes(@PathParam("id") String id) {
		StringWriter sw = new StringWriter();
		try {
			Marshaller jaxbMarshaller = JAXBContext.newInstance(Notes.class).createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			User user = new NoteController().findUserById(Long.parseLong(id));
			jaxbMarshaller.marshal(user.getNotes(), sw);
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(sw.toString()).build();
	}

	@PermitAll
	@GET
	@Produces("application/xml")
	@Path("/users/{id}/notes/{note_id}")
	public Response getUserNote(@PathParam("id") String id, @PathParam("note_id") String note_id) {
		StringWriter sw = new StringWriter();
		try {
			Marshaller jaxbMarshaller = JAXBContext.newInstance(Note.class).createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			Note note = new NoteController().findNoteById(Long.parseLong(note_id));
			jaxbMarshaller.marshal(note, sw);
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(sw.toString()).build();
	}

	@PermitAll
	@DELETE
	@Path("/users/{id}/notes/{note_id}")
	public Response deleteUserNote(@PathParam("id") String id, @PathParam("note_id") String note_id) {
		boolean result = false;
		try {
			User user = new NoteController().findUserById(Long.parseLong(id));
			// Note note = new
			// NoteController().findNoteById(Long.parseLong(note_id));
			List<Note> notes = user.getNotes().getNote();
			for (Note note : notes) {
				if (Long.parseLong(note_id) == note.getId()) {
					user.getNotes().getNote().remove(note);
					break;
				}
			}
			result = new NoteController().updateUser(user);
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(Boolean.toString(result)).build();
	}
}
