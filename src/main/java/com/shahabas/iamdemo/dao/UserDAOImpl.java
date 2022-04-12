package com.shahabas.iamdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shahabas.iamdemo.entity.Authority;
import com.shahabas.iamdemo.entity.Quote;
import com.shahabas.iamdemo.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private EntityManager entityManager;
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<User> getUsers() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User", User.class);		List<User> userList = query.getResultList();
		return userList;
	}

	@Override
	public void deleteById(String id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<User> theQuery = currentSession.createQuery("from User where username=:userId");
		theQuery.setParameter("userId", id);
		User myUser = theQuery.getSingleResult();
		
		Query authorityDeletionQuery = currentSession.createQuery("delete from Authority where username=:userName");
		authorityDeletionQuery.setParameter("userName", id);
		authorityDeletionQuery.executeUpdate();
		
		Query quoteDeletionQuery = currentSession.createQuery("delete from Quote where user_id=:userId");
		quoteDeletionQuery.setParameter("userId", myUser.getId());
		quoteDeletionQuery.executeUpdate();
		
		Query userDeletionQuery = currentSession.createQuery("delete from User where username=:userName");
		userDeletionQuery.setParameter("userName", id);
		userDeletionQuery.executeUpdate();
	}

	@Override
	public void saveQuote(Quote quote, String id) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println("The name is " + id);
		Query<User> theQuery = currentSession.createQuery("from User where username=:userId");
		theQuery.setParameter("userId", id);
		User myUser = theQuery.getSingleResult();
		myUser.addQuote(quote);
		//currentSession.saveOrUpdate(myUser);
		System.out.println(myUser);
	}

	@Override
	public void getQuotes(int id) {

	}

	@Override
	public void addNewUser(User user, Authority authority) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println(authority);
		currentSession.saveOrUpdate(user);
		currentSession.saveOrUpdate(authority);
	}

}
