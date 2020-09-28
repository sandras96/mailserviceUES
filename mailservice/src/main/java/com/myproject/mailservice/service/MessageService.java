package com.myproject.mailservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.dto.MessageDTO;
import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.entity.Message;
import com.myproject.mailservice.repository.MessageRepository;


@Service
public class MessageService implements MessageInterface{
	
	
	@Autowired
	MessageRepository messageRepository;


	@Override
	public Message getOne(Long MessageId) {
		return messageRepository.getOne(MessageId);
	}

	@Override
	public Message save(Message Message) {
		return messageRepository.save(Message);
	}

	@Override
	public void delete(Long id) {
		Message deletedMessage = getOne(id);
		if(deletedMessage!=null){
			messageRepository.deleteById(id);
		}
	}


	@Override
	public List<Message> findMessagesByAccountId(Long id) {
		// TODO Auto-generated method stub
		return messageRepository.findMessagesByAccountId(id);
	}




	@Override
	public Message edit(MessageDTO Message, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findBySubject(Long id) {
		// TODO Auto-generated method stub
		return messageRepository.findAllSortBySubject(id);
	}

	@Override
	public List<Message> findBySender(Long id) {
		// TODO Auto-generated method stub
		return messageRepository.findAllSortBySender(id);
	}

	@Override
	public List<Message> findByDatetime(Long id) {
		// TODO Auto-generated method stub
		return messageRepository.findAllSortByDatetime(id);
	}


	@Override
	public List<Message> findByTags_IdAndAccountId(Long tagId, Long accountId) {
		// TODO Auto-generated method stub
		return messageRepository.findByTags_IdAndAccountId(tagId, accountId);
	}

	@Override
	public List<Message> findMessagesByEuserId(Long id) {
		// TODO Auto-generated method stub
		return messageRepository.findMessagesByEuserId(id);
	}

	@Override
	public List<Message> findByTags_IdAndEuserId(Long tagId, Long userId) {
		// TODO Auto-generated method stub
		return messageRepository.findByTags_IdAndEuserId(tagId, userId);
	}

	@Override
	public List<Message> findAll() {
		// TODO Auto-generated method stub
		return messageRepository.findAll();
	}





}
