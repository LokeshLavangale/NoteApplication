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
import org.hibernate.cfg.Configuration;

import com.lokesh.note.model.Note;
import com.lokesh.note.model.Notes;
import com.lokesh.note.model.User;

/**
 * @author Lokesh Lavangale
 *
 * Class to Test functionality
 */
public class NoteApplication {

	/**
	 * 
	 */
	public NoteApplication() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Note Application");
		
		Configuration cfg=new Configuration();  
	    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
	      
	    //creating seession factory object  
	    SessionFactory factory=cfg.buildSessionFactory();  
	      
	    //creating session object  
	    Session session=factory.openSession();  
	      
	    //creating transaction object  
	    Transaction t=session.beginTransaction();  
	    
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
	    
	    
	    session.persist(user);//persisting the object  
	      
	    t.commit();//transaction is commited  
	    session.close();  
	      
	    System.out.println("Note successfully saved"); 
	}

	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
			
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
