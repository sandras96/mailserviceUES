package com.myproject.mailservice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.dto.ContactDTO;
import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.repository.ContactRepository;


@Service
public class ContactService implements ContactInterface {
	
	@Autowired
	ContactRepository contactRepository;
	
	
	@Override
	public List<Contact> getAll(){
		return contactRepository.findAll();
	}
	
	
	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
	
	
	


	@Override
	public void delete(Long id) {
		Contact deletedContact = getOne(id);
		if(deletedContact!=null){
			contactRepository.deleteById(id);
		}
	}

	@Override
	public Contact getOne(Long contactId) {
		// TODO Auto-generated method stub
		return contactRepository.getOne(contactId);
	}


	@Override
	public Contact edit(ContactDTO contact, Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Contact> findContactsByEuserId(Long id) {
		return contactRepository.findContactsByEuserId(id);
	}


	@Override
	public List<Contact> findBynote(String parameter) {
		// TODO Auto-generated method stub
		return contactRepository.findBynote(parameter);
	}
	

	
	

}
