package com.myproject.mailservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer>{

	List<Tag> findTagsByEuserId(Long id);
//	List<Tag> findTagsByAccountId(Long id);
}
