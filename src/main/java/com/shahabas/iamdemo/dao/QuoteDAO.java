package com.shahabas.iamdemo.dao;

import java.util.List;

import com.shahabas.iamdemo.entity.Quote;

public interface QuoteDAO {
	public List<Quote> getQuotes();
	public List<Quote> getQuotesByApproval(int approval);
	public void approveQuote(int quoteId);
}
