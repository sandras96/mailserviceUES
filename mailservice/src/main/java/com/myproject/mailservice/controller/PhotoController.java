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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.ContactDTO;
import com.myproject.mailservice.dto.PhotoDTO;
import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Photo;
import com.myproject.mailservice.repository.PhotoRepository;
import com.myproject.mailservice.service.ContactInterface;
import com.myproject.mailservice.service.UserInterface;


@RestController
@RequestMapping(value = "mailservice/photo")
public class PhotoController {

	
	@Autowired
	private ContactInterface contactService;
	
	
	@Autowired
	PhotoRepository photoRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @GetMapping(value = "/contact/{id}")
	    public ResponseEntity<List<PhotoDTO>> getPhotosByContact(@PathVariable("id") Long id){
		 	logger.info("GET request for all photos from contact with id: " + id);
		 	List<Photo> photos=photoRepository.findPhotoByContactId(id);
	        List<PhotoDTO>photosDTO=new ArrayList<>();
	       
	        if(photos == null)
	            return new ResponseEntity<List<PhotoDTO>>(HttpStatus.NOT_FOUND);
	        else {
	            for (Photo p: photos)
	                photosDTO.add(new PhotoDTO(p));
	        }
	        return new ResponseEntity<List<PhotoDTO>>(photosDTO,HttpStatus.OK);
	    }
	 
}
