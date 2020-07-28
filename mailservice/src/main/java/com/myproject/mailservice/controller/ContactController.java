package com.myproject.mailservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.ContactDTO;
import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.service.ContactInterface;
import com.myproject.mailservice.service.UserInterface;

@RestController
@RequestMapping(value = "mailservice/contacts")
public class ContactController {

	
	@Autowired
	private ContactInterface contactService;
	
	@Autowired
	private UserInterface userService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping(value="/{id}")
		public ResponseEntity<ContactDTO> getContactById(@PathVariable("id")Long id){
			logger.info("GET request for contact with id: " + id);
			Contact contact = contactService.getOne(id);
			if(contact == null) {
				logger.error("Contact with id: "+ id +" not found.");
				return new ResponseEntity<ContactDTO>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
			}
	}
	
	 @GetMapping(value = "/user/{id}")
	    public ResponseEntity<List<ContactDTO>> getContactsByUser(@PathVariable("id") Long id){
		 	logger.info("GET request for all contacts from user with id: " + id);
		 	List<Contact> contacts=contactService.findContactsByEuserId(id);
	        List<ContactDTO>contactsDTO=new ArrayList<>();
	        if(contacts == null)
	            return new ResponseEntity<List<ContactDTO>>(HttpStatus.NOT_FOUND);
	        else {
	            for (Contact c: contacts)
	                contactsDTO.add(new ContactDTO(c));
	        }
	        return new ResponseEntity<List<ContactDTO>>(contactsDTO,HttpStatus.OK);
	    }
	 
	 
	 @PostMapping(value="/addContact")
		public ResponseEntity<ContactDTO> addContact(@RequestParam("displayName") String displayname,
													@RequestParam("email") String email,
													@RequestParam("firstname") String firstname,
													@RequestParam("lastname") String lastname,
													@RequestParam("text") String text,
													@RequestParam("euser") Long id){
		 	logger.info("Post request for adding new contact with firstname: " + firstname +" for user with id: " + id);
			Contact contact = new Contact();
			contact.setDisplayName(displayname);
			contact.setEmail(email);
			contact.setFirstname(firstname);
			contact.setLastname(lastname);
			contact.setText(text);
			contact.setEuser(userService.getOne(id));
			
			contact = contactService.save(contact);
			return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
		}
	 

	@PutMapping(value="/editContact/{id}", consumes="application/json")
		public ResponseEntity<ContactDTO> updateContact(@RequestBody ContactDTO contactDTO,@PathVariable("id")Long id){
			logger.info("Put request for updating contact with id: " + id);
			Contact contact = contactService.getOne(id);
			System.out.println(id);
			if(contact == null) {
				return new ResponseEntity<ContactDTO>(HttpStatus.BAD_REQUEST);
			}
				contact.setDisplayName(contactDTO.getDisplayName());
				contact.setEmail(contactDTO.getEmail());
				contact.setFirstname(contactDTO.getFirstname());
				contact.setLastname(contactDTO.getLastname());
				contact.setText(contactDTO.getText());
				
				contact = contactService.save(contact);
				return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
			
		}
	 
	 @DeleteMapping(value="/deleteContact/{id}")
		public ResponseEntity<Void> deleteContact(@PathVariable("id")Long id){
		 	logger.info("Delete request, delete contact with id: " + id);
			Contact contact = contactService.getOne(id);
			if(contact == null) {
				logger.error("Contact with id: "+ id +" not found.");
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}else {
				contactService.delete(id);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
	 
		
}
