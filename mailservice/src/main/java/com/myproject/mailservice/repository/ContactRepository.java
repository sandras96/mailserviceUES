package com.myproject.mailservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myproject.mailservice.entity.Contact;



public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Contact getByEmail(String email);
	Contact getByDisplayName(String displayName);
	Contact findByfirstname(String firstname);
//	@Query("SELECT c FROM Contact c  WHERE c.euser.id = :userId")
//	public List<Contact> findContactsByUser(@Param("userId") Long id);
	List<Contact> findContactsByEuserId(Long id);
	
	
}
