package com.shahabas.iamdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shahabas.iamdemo.dao.QuoteDAO;
import com.shahabas.iamdemo.entity.Quote;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	QuoteDAO quoteDAO;
	
	public QuoteServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public List<Quote> getQuotes() {
		return quoteDAO.getQuotes();
	}

	@Override
	@Transactional
	public List<Quote> getQuotesByApproval(int approval) {
		return quoteDAO.getQuotesByApproval(approval);
	}

	@Override
	@Transactional
	public void approveQuote(int quoteId) {
		quoteDAO.approveQuote(quoteId);
		System.out.println("Quote has been approved!");
	}

}
