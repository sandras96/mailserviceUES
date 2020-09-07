package com.myproject.mailservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.MessageDTO;
import com.myproject.mailservice.dto.TagDTO;
import com.myproject.mailservice.entity.Message;
import com.myproject.mailservice.entity.Tag;
import com.myproject.mailservice.service.MessageInterface;
import com.myproject.mailservice.service.TagInterface;

@RestController
@RequestMapping(value="mailservice/tags")
public class TagController {
	
	
	@Autowired
	private TagInterface tagService;
	
	
	@Autowired
	private MessageInterface messageService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping(value= "/getAll/{userId}")
	public ResponseEntity<List<TagDTO>>getTagsByEuserId(@PathVariable("userId") Long userId){
		logger.info("GET Method, request for all tags by userId.");
        List<Tag>tags=tagService.findTagsByEuserId(userId);
        List<TagDTO>tagsDTOS=new ArrayList<>();
        for (Tag tag:tags)
            tagsDTOS.add(new TagDTO(tag));

        return new ResponseEntity<List<TagDTO>>(tagsDTOS,HttpStatus.OK);
    }
	
	
	  @GetMapping(value = "/message/{tagId}/{userId}")  
	  public ResponseEntity<List<MessageDTO>>getMessagessByTagAndUserId(@PathVariable("tagId") Long tagId, @PathVariable("userId") Long userId ){
		  logger.info("GET Method, request for all messages which have tag with id: "+tagId+"."); 
		  System.out.println("messagessss with tag: " + tagId + "userrr" + userId);
		  List<Message> messages=messageService.findByTags_IdAndEuserId(tagId, userId);
		  List<MessageDTO>messagesDTO=new ArrayList<>(); 
		  	if(messages == null) {
			  logger.error("Tag with id: "+tagId+" not found."); 
			  return new  ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND); } 
		  	for(Message m: messages) 
		  		messagesDTO.add(new MessageDTO(m)); 
		  	return new ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK); }
	 
	/*
	 * @GetMapping(value= "/getAll/{accountId}") public
	 * ResponseEntity<List<TagDTO>>getTagsByAccountId(@PathVariable("accountId")
	 * Long accountId ){
	 * logger.info("GET Method, request for all tags by accountId.");
	 * List<Tag>tags=tagService.findTagsByAccountId(accountId);
	 * List<TagDTO>tagsDTOS=new ArrayList<>(); for (Tag tag:tags) tagsDTOS.add(new
	 * TagDTO(tag));
	 * 
	 * return new ResponseEntity<List<TagDTO>>(tagsDTOS,HttpStatus.OK); }
	 * 
	 * 
	 * @GetMapping(value = "/message/{tagId}/{accountId}") public
	 * ResponseEntity<List<MessageDTO>>getMessagessByTag(@PathVariable("tagId") Long
	 * tagId, @PathVariable("accountId") Long accountId ){
	 * logger.info("GET Method, request for all messages which have tag with id: "
	 * +tagId+"."); List<Message>
	 * messages=messageService.findByTags_IdAndAccountId(tagId, accountId);
	 * List<MessageDTO>messagesDTO=new ArrayList<>(); if(messages == null) {
	 * logger.error("Tag with id: "+tagId+" not found."); return new
	 * ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND); } for(Message m:
	 * messages) messagesDTO.add(new MessageDTO(m)); return new
	 * ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK); }
	 */
}
