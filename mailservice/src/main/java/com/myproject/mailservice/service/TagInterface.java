package com.myproject.mailservice.service;

import java.util.List;

import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.entity.Tag;

public interface TagInterface {
	
	List<Tag> findAll();
	List<Tag> findTagsByAccountId(Long id);

}
