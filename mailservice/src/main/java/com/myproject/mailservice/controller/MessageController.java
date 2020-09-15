package com.myproject.mailservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.AccountDTO;
import com.myproject.mailservice.dto.MessageDTO;
import com.myproject.mailservice.elasticsearch.repository.MessageRepositoryElasticSearch;
import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.entity.Message;
import com.myproject.mailservice.service.AccountInterface;
import com.myproject.mailservice.service.MessageInterface;

@RestController
@RequestMapping(value="mailservice/messages")
public class MessageController {

	@Autowired
	private AccountInterface accountService;
	
	@Autowired
	private MessageInterface messageService;
	
	@Autowired(required=true)
	private MessageRepositoryElasticSearch messageRepo;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @GetMapping(value = "/user/{id}")
	    public ResponseEntity<List<MessageDTO>> getMessagesByUser(@PathVariable("id") Long id){
		 	logger.info("GET request for all messages from user with id: " + id);
		 	List<Message> messages=messageService.findMessagesByEuserId(id);
	        List<MessageDTO> messagesDTO=new ArrayList<>();
	        if(messages == null)
	            return new ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND);
	        else {
	            for (Message m: messages)
	                messagesDTO.add(new MessageDTO(m));
	        }
	        return new ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK);
	    }
	 
	 @GetMapping(value = "/account/{id}")
	    public ResponseEntity<List<MessageDTO>> getMessagesByAccount(@PathVariable("id") Long id){
		 	logger.info("GET request for all messages from account with id: " + id);
		 	List<Message> messages=messageService.findMessagesByAccountId(id);
	        List<MessageDTO>messagesDTO=new ArrayList<>();
	        if(messages == null)
	            return new ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND);
	        else {
	            for (Message m:messages)
	                messagesDTO.add(new MessageDTO(m));
	        }
	        return new ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK);
	    }
	 
	 @GetMapping(value="/{id}")
		public ResponseEntity<MessageDTO> getMessage(@PathVariable("id")Long id){
		 logger.info("GET request for message with id: " + id);
			Message message = messageService.getOne(id);
			if(message == null) {
				logger.error("Message with id: "+ id +" not found.");
				return new ResponseEntity<MessageDTO>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);
		}
	 
	 @DeleteMapping(value="/deleteMessage/{id}")
		public ResponseEntity<Void> deleteMessage(@PathVariable("id")Long id){
		 	logger.info("Delete request, delete message with id: " + id);
		 	Message message = messageService.getOne(id);
			if(message == null) {
				logger.error("Message with id: "+ id +" not found.");
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}else {
				messageService.delete(id);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
	 
	 @GetMapping(value = "/orderbysubject/{id}")
	    public ResponseEntity<List<MessageDTO>> getMessagesSortBySubject(@PathVariable("id")Long id){
		 	logger.info("GET Method, request for all messages sorted by subject from user with id: " + id );
	        List<Message> messages=messageService.findBySubject(id);
	        List<MessageDTO> messagesDTO=new ArrayList<>();
	        for (Message message : messages) {
	            messagesDTO.add(new MessageDTO(message));
	        }
	        return new ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK);
	    }
	 
	 @GetMapping(value = "/orderbysender/{id}")
	    public ResponseEntity<List<MessageDTO>> getMessagesSortBySender(@PathVariable("id")Long id){
		 	logger.info("GET Method, request for all messages sorted by sender from user with id: " + id);
	        List<Message> messages=messageService.findBySender(id);
	        List<MessageDTO> messagesDTO=new ArrayList<>();
	        for (Message message : messages) {
	            messagesDTO.add(new MessageDTO(message));
	        }
	        return new ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK);
	    }
	 
	 @GetMapping(value = "/orderbydatetime/{id}")
	    public ResponseEntity<List<MessageDTO>> getMessagesSortByDatetime(@PathVariable("id")Long id){
		 	logger.info("GET Method, request for all messages sorted by datetitme from user with id: " + id);
	        List<Message> messages=messageService.findByDatetime(id);
	        List<MessageDTO> messagesDTO=new ArrayList<>();
	        for (Message message : messages) {
	            messagesDTO.add(new MessageDTO(message));
	        }
	        return new ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK);
	    }
	 
		/*
		 * @GetMapping(value = "/account/orderbysubject/{id}") public
		 * ResponseEntity<List<MessageDTO>>
		 * getMessagesSortBySubject(@PathVariable("id")Long id){ logger.
		 * info("GET Method, request for all messages sorted by subject from account with id: "
		 * + id); System.out.println("ID JE" + id); List<Message>
		 * messages=messageRepo.findByAccountOrderBySubjectAsc(id);
		 * System.out.println("LISTA PORUKA JE "); List<MessageDTO> messagesDTO=new
		 * ArrayList<>(); for (Message message : messages) {
		 * message.setAccount(accountService.getOne(id)); messagesDTO.add(new
		 * MessageDTO(message)); } return new
		 * ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK); }
		 */
}
