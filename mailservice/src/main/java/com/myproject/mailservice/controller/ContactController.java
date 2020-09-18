package com.myproject.mailservice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import com.myproject.mailservice.dto.ContactDTO;
import com.myproject.mailservice.dto.PhotoDTO;
import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Photo;
import com.myproject.mailservice.repository.PhotoRepository;
import com.myproject.mailservice.service.ContactInterface;
import com.myproject.mailservice.service.UserInterface;

@RestController
@RequestMapping(value = "mailservice/contacts")
public class ContactController {

	
	@Autowired
	private ContactInterface contactService;
	
	@Autowired
	private UserInterface userService;
	
	@Autowired
	PhotoRepository photoRepository;
	
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
            for (Contact c: contacts) {
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
				return new ResponseEntity<ContactDTO>(HttpStatus.NOT_FOUND);
			}
			
			if(contactDTO.getDisplayName().equals("")) {
				contact.setDisplayName(contact.getDisplayName());
			}else {
				contact.setDisplayName(contactDTO.getDisplayName());
				}
			if(contactDTO.getEmail().equals("")) {
				contact.setEmail(contact.getEmail());
			}else {
				contact.setEmail(contactDTO.getEmail());
				}
			if(contactDTO.getFirstname().equals("")) {
				contact.setFirstname(contact.getFirstname());
			}else {
				contact.setFirstname(contactDTO.getFirstname());
				}
			if(contactDTO.getLastname().equals("")) {
				contact.setLastname(contact.getLastname());
			}else {
				contact.setLastname(contactDTO.getLastname());
				}
			if(contactDTO.getText().equals("")) {
				contact.setText(contact.getText());
			}else {
				contact.setText(contactDTO.getText());
				}
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
	 
	 @PostMapping(value = "/upload_photo", consumes = {"multipart/mixed", "multipart/form-data"})
	    public Photo uploadImage(@RequestParam("id") Long id,@RequestParam("photo") MultipartFile photo) throws Exception{
		 	logger.info("POST Method, upload photo for contact with id: "+id+".");   
		 	System.out.println("id je SLIKE" + id);
	     //   Photo uploadedPhoto = new Photo(photo.getOriginalFilename(), photo.getInputStream().toString(), photo.getBytes(), contactService.getOne(id));
	   //     String url = "http://localhost:8080/test/" + photo.getOriginalFilename();
	   //     HttpHeaders headers = new HttpHeaders();
	  //      headers.setLocation(URI.create(url));
	 //       Photo uploadedPhoto = new Photo(photo.getOriginalFilename(), url, photo.getBytes(), contactService.getOne(id));
	//        System.out.println("uploaded photo je " + uploadedPhoto + "path je " + photo.getInputStream().toString() + "urllll je " + url);
	//        photoRepository.save(uploadedPhoto);
	        
		 	
	    //    return new ResponseEntity<>(headers, HttpStatus.CREATED);
	        
		 	File file = convert(photo);
		 	String path =file.getAbsolutePath(); //getAbsolutePath()
		 	System.out.println("pathhh je " + path);
		 	Photo uploadedPhoto = new Photo(photo.getOriginalFilename(), path, photo.getBytes(), contactService.getOne(id));
		 	System.out.println("uploadddd " + uploadedPhoto);
		 	return photoRepository.save(uploadedPhoto);
	    }
	 	public File convert(MultipartFile file) throws IOException{
		 	  File convFile = new File(file.getOriginalFilename());
		 	  convFile.createNewFile(); 
		 	  FileOutputStream fos = new FileOutputStream(convFile); 
		 	  fos.write(file.getBytes());
		 	  fos.close(); 
		 	  return convFile;
		 	}
	}
