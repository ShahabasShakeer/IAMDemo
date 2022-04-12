package com.shahabas.iamdemo.service;

import java.util.List;

import com.shahabas.iamdemo.entity.Quote;

public interface QuoteService {
	public List<Quote> getQuotes();
	public List<Quote> getQuotesByApproval(int approval);
	public void approveQuote(int quoteId);
}
