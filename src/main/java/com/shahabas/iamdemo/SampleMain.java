package com.shahabas.iamdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shahabas.iamdemo.entity.Contact;
import com.shahabas.iamdemo.service.ContactService;
import com.shahabas.iamdemo.service.UserService;

@Component
public class SampleMain implements CommandLineRunner {
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		
		/*Contact contactOne = new Contact("Laaza@icecream.com", "+56-4331-460718");
		Contact contactTwo = new Contact("locopops@icecream.com", "+56-7891-477718");
		
		contactService.save(contactOne);
		contactService.save(contactTwo);
		
		Address home = new Address("Ezhilode, Kannur, Kerala");
		Address work = new Address("Payyanur, Kannur, Kerala");
		
		contactService.saveAddress(home, contactOne.getId());
		contactService.saveAddress(work, contactOne.getId());
		
		System.out.println(contactService.getAddress(contactOne.getId()));*/
		
		//contactService.deleteById(7);
		
		
		
		List<Contact> myContacts = contactService.getContacts();
		for (Contact c : myContacts) {
			System.out.println("Email: " + c.getEmail());
		}
	}

}
