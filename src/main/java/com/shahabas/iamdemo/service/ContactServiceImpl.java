package com.shahabas.iamdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shahabas.iamdemo.dao.ContactDAO;
import com.shahabas.iamdemo.entity.Address;
import com.shahabas.iamdemo.entity.Contact;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactDAO contactDao;
	
	public ContactServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public void save(Contact contact) {
		contactDao.save(contact);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		contactDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Contact> getContacts() {
		return contactDao.getContacts();
	}

	@Override
	@Transactional
	public void saveAddress(Address address, int id) {
		contactDao.saveAddress(address, id);
	}

	@Override
	@Transactional
	public List<Address> getAddress(int id) {
		return contactDao.getAddress(id);
	}

}
