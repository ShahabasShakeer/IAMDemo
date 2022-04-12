package com.shahabas.iamdemo.service;

import java.util.List;

import com.shahabas.iamdemo.entity.Quote;
import com.shahabas.iamdemo.entity.User;

public interface UserService {
	public List<User> getUsers();
	public void deleteById(String id);
	public void saveQuote(Quote quote, String id);
	public void getQuotes(int id);
	public void addNewUser(String role, String username, String password);
}
