package com.myproject.mailservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myproject.mailservice.entity.Contact;



public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Contact getByEmail(String email);
	Contact getByDisplayName(String displayName);
	List<Contact> findContactsByEuserId(Long id);
	
	@Query(value="SELECT * FROM contact WHERE contact_note LIKE ?", nativeQuery = true)
	List<Contact> findBynote(String text);
	
}
