package com.myproject.mailservice.service;

import java.util.List;


import com.myproject.mailservice.dto.ContactDTO;
import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.entity.Contact;

public interface ContactInterface {

	Contact getOne(Long contactId);
	List<Contact> findContactsByEuserId(Long id);
	Contact save(Contact contact);
	Contact edit(ContactDTO contact,Long id);
	void delete(Long id);
	List<Contact> getAll();
}
