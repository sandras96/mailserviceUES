package com.myproject.mailservice.service;

import java.util.List;

import com.myproject.mailservice.dto.MessageDTO;
import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Message;

public interface MessageInterface {

	Message getOne(Long MessageId);
	Message save(Message Message);
	Message edit(MessageDTO Message,Long id);
	void delete(Long id);
	List<Message> findMessagesByAccountId(Long id);
	List<Message> findBySubject(Long id);
	List<Message> findBySender(Long id);
	List<Message> findByDatetime(Long id);
	List<Message> findByContent(String parameter, Long id);
	List<Message> findByTags_IdAndAccountId(Long tagId, Long accountId);
}
