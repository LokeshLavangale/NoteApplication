/**
 * 
 */
package com.lokesh.note.service;

import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.lokesh.note.controller.NoteController;
import com.lokesh.note.model.User;
import com.lokesh.note.model.Users;

/**
 * @author Lokesh Lavnagale
 *
 */
@Path("/service")
public class NoteService {

	@GET
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

	@GET
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
}
