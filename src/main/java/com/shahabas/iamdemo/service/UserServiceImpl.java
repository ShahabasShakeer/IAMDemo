package com.shahabas.iamdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shahabas.iamdemo.dao.UserDAO;
import com.shahabas.iamdemo.entity.Authority;
import com.shahabas.iamdemo.entity.Quote;
import com.shahabas.iamdemo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	/*@Autowired
	private BCryptPasswordEncoder passwordEncoder;*/
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		userDAO.deleteById(id);
		System.out.println("User " + id + " has been deleted!");
	}

	@Override
	@Transactional
	public void saveQuote(Quote quote, String id) {
		userDAO.saveQuote(quote, id);
		System.out.println("Quote has been added for " + id);
	}

	@Override
	@Transactional
	public void getQuotes(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void addNewUser(String role, String username, String password) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// Set User
		User newUser = new User(username, 1, encoder.encode(password));
		newUser.setId(0);
		// Set Authority
		Authority newAuthority = new Authority();
		newAuthority.setAuthority(role);
		newAuthority.setId(0);
		newAuthority.setUsername(username);
		
		userDAO.addNewUser(newUser, newAuthority);
		System.out.println("New user has been added!");
	}

}
