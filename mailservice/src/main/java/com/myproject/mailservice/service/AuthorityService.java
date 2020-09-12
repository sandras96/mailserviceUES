package com.myproject.mailservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.entity.Authority;
import com.myproject.mailservice.repository.AuthorityRepository;





@Service
public class AuthorityService implements AuthorityInterface {

	@Autowired
	AuthorityRepository authorityRepository;
	
	@Override
	public Authority getByName(String name) {
		
		return authorityRepository.getByName(name);
	}

}
