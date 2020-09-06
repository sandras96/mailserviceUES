package com.myproject.mailservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.entity.Tag;
import com.myproject.mailservice.repository.TagRepository;

@Service
public class TagService implements TagInterface {

	
	@Autowired
	TagRepository tagRepository;

	@Override
	public List<Tag> findAll() {
		// TODO Auto-generated method stub
		return tagRepository.findAll();
	}

	@Override
	public List<Tag> findTagsByAccountId(Long id) {
		// TODO Auto-generated method stub
		return tagRepository.findTagssByAccountId(id);
	}


}
