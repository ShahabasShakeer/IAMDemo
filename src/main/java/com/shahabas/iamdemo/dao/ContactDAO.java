package com.shahabas.iamdemo.dao;

import java.util.List;

import com.shahabas.iamdemo.entity.Address;
import com.shahabas.iamdemo.entity.Contact;

public interface ContactDAO {
	public void save(Contact contact);
	public void deleteById(int id);
	public List<Contact> getContacts();
	public void saveAddress(Address address, int id);
	public List<Address> getAddress(int id);
}
