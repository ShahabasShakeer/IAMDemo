package com.shahabas.iamdemo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shahabas.iamdemo.entity.Contact;
import com.shahabas.iamdemo.entity.Quote;
import com.shahabas.iamdemo.entity.User;
import com.shahabas.iamdemo.service.ContactService;
import com.shahabas.iamdemo.service.QuoteService;
import com.shahabas.iamdemo.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuoteService quoteService;
	
	@GetMapping("/")
	public String showMainPage(Model model) {
		List<Quote> quoteList = quoteService.getQuotesByApproval(1);
		model.addAttribute("quotes", quoteList);
		Quote theQuote = new Quote();
		model.addAttribute("quote", theQuote);
		return "main-page";
	}
	
	@GetMapping("/showFormPage")
	public String showFormPage(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		return "form-page";
	}
	
	@PostMapping("/addContact")
	public String addContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "form-page";
		} else {
			contactService.save(contact);
			return "redirect:/contactList";
		}
	}
	
	@GetMapping("/deleteContact")
	public String deleteContact(@RequestParam("contactId") int id, Model theModel) {
		contactService.deleteById(id);
		//System.out.println("Deleting function is working!");
		return "redirect:/contactList";
	}
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "fancy-login";
	}
	
	@PostMapping("/addQuote")
	public String addQuote(@ModelAttribute("quote") Quote quote, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "access-denied";
		} else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
				//System.out.println("My name is: " + authentication.getName());
			    userService.saveQuote(quote, authentication.getName());
			}
			return "redirect:/";
		}
	}
	
	@GetMapping("/contactList")
	public String showContacts(Model theModel) {
		List<Contact> myContacts = contactService.getContacts();
		theModel.addAttribute("contacts", myContacts);
		return "contact-list";
	}
	
	@PostMapping("/addUser")
	public String addUser(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		userService.addNewUser(role, username, password);
		return "redirect:/";
	}
	
	@GetMapping("/userForm")
	public String showUserForm() {
		return "user-form";
	}
	
	@GetMapping("/showPendingQuotes")
	public String showPendingQuotes(Model theModel) {
		List<Quote> allQuotes = quoteService.getQuotesByApproval(0);
		theModel.addAttribute("quotes", allQuotes);
		return "pending-quotes";
	}
	
	@GetMapping("/userList")
	public String showUsers(Model theModel) {
		List<User> users = userService.getUsers();
		theModel.addAttribute("users", users);
		return "user-list";
	}
	
	@GetMapping("/approveQuote")
	public String approveQuote(@RequestParam("quoteId") int quoteId, Model theModel) {
		quoteService.approveQuote(quoteId);
		return "redirect:/showPendingQuotes";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userName") String theId, Model theModel) {
		userService.deleteById(theId);
		//System.out.println("Deleting function is working!");
		return "redirect:/userList";
	}
	
	@GetMapping("/accessDenied")
	public String showAccessDenied() {
		return "access-denied";
	}

}
