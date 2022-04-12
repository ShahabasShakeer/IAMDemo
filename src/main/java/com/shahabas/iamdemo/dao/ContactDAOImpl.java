package com.shahabas.iamdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shahabas.iamdemo.entity.Address;
import com.shahabas.iamdemo.entity.Contact;

@Repository
public class ContactDAOImpl implements ContactDAO {

	private EntityManager entityManager;
	
	@Autowired
	public ContactDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ContactDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Contact contact) {
		Session currentSession = entityManager.unwrap(Session.class);
		contact.setId(0);
		currentSession.saveOrUpdate(contact);
		System.out.println("Contact " + contact.getEmail() + " has been created");
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete from Contact where id=:contactId");
		theQuery.setParameter("contactId", id);
		theQuery.executeUpdate();
		System.out.println("Contact " + id + " has been deleted!");
	}

	@Override
	public List<Contact> getContacts() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Contact> theQuery = currentSession.createQuery("from Contact", Contact.class);
		List<Contact> contacts = theQuery.getResultList();
		return contacts;
	}

	@Override
	public void saveAddress(Address address, int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Contact contact = currentSession.get(Contact.class, id);
		contact.addAddress(address);
		System.out.println("Address has been added successfully!");
	}

	@Override
	public List<Address> getAddress(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Contact> theQuery = currentSession.createQuery("select c from Contact c JOIN FETCH c.addresses where c.id=:contactId", Contact.class);
		
		theQuery.setParameter("contactId", id);
		
		Contact tempContact = theQuery.getSingleResult();
		
		System.out.println("Fetched: " + tempContact);
		//Contact contact = currentSession.get(Contact.class, id);
		return tempContact.getAddresses();
	}

}
