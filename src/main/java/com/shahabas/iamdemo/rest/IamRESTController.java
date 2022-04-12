package com.shahabas.iamdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahabas.iamdemo.entity.Quote;
import com.shahabas.iamdemo.service.QuoteService;

@RestController
@RequestMapping("/api")
public class IamRESTController {

	@Autowired
	QuoteService quoteService; 
	
	public IamRESTController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/quotes")
	public List<Quote> getStudents() {
		return quoteService.getQuotesByApproval(1);
	}
	
}
