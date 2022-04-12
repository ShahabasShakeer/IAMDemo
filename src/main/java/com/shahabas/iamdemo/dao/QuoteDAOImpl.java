package com.shahabas.iamdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shahabas.iamdemo.entity.Quote;

@Repository
public class QuoteDAOImpl implements QuoteDAO {

	private EntityManager entityManager;
	
	public QuoteDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public QuoteDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Quote> getQuotes() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Quote> query = currentSession.createQuery("from Quote", Quote.class);
		List<Quote> quoteList = query.getResultList();
		return quoteList;
	}

	@Override
	public List<Quote> getQuotesByApproval(int approval) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Quote> query = currentSession.createQuery("from Quote where approved=:approval", Quote.class);
		query.setParameter("approval", approval);
		List<Quote> quoteList = query.getResultList();
		return quoteList;
	}

	@Override
	public void approveQuote(int quoteId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Quote> query = currentSession.createQuery("from Quote where id=:quoteId", Quote.class);
		query.setParameter("quoteId", quoteId);
		Quote quote = query.getSingleResult();
		quote.setApproved(1);
		currentSession.saveOrUpdate(quote);
	}


}
