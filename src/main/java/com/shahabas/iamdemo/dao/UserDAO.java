package com.shahabas.iamdemo.dao;

import java.util.List;

import com.shahabas.iamdemo.entity.Authority;
import com.shahabas.iamdemo.entity.Quote;
import com.shahabas.iamdemo.entity.User;

public interface UserDAO {
	public List<User> getUsers();
	public void deleteById(String id);
	public void saveQuote(Quote quote, String id);
	public void getQuotes(int id);
	public void addNewUser(User user, Authority authority);
}
