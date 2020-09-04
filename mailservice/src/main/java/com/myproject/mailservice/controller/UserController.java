package com.myproject.mailservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.UserDTO;
import com.myproject.mailservice.entity.User;
import com.myproject.mailservice.service.UserInterface;

@RestController
@RequestMapping(value="mailservice/users")
public class UserController {
	
	@Autowired
	private UserInterface userService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping(value="/{username}/{password}")
	public ResponseEntity<UserDTO> findByUsernameAndPassword(@PathVariable("username")String username, @PathVariable("password") String password){
		logger.info("GET request for user by username: "+username);
		User user = userService.getByUsernameAndUserPassword(username, password);
		System.out.println("usr" + username + "pass" + password);
		if(user == null) {
			logger.error("User with username: "+username+" not found.");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		
	}

}