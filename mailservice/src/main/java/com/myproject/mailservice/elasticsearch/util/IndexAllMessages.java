package com.myproject.mailservice.elasticsearch.util;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.mailservice.entity.Message;
import com.myproject.mailservice.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndexAllMessages {
	
	
	@Autowired
	ElasticIndexer elasticIndexing;
	
	@Autowired
	MessageRepository messageRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Transactional(rollbackFor = Throwable.class, readOnly = true)
	public void execute() throws IOException {
		final List<Message> messages = messageRepository.findAll();
		final List<MessageElastic> messageElastic = messages.parallelStream().map(message ->
		{
			return new MessageElastic(message);
		}).collect(Collectors.toList());
		
	//	elasticIndexing.indexAll(messageElastic);
	}
	
	

}
