package com.myproject.mailservice.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.myproject.mailservice.entity.Message;
import com.myproject.mailservice.entity.Tag;

public class TagDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	
	private Long id;
	private String name;
	private UserDTO user;
	private AccountDTO account;
	private List<MessageDTO> messages = new ArrayList<>();
	
	public TagDTO(Long id, String name, UserDTO user, AccountDTO account) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.account = account;
		
	}
	public TagDTO(Tag tag) {
		this(tag.getId(), tag.getName(), new UserDTO(tag.getEuser()), new AccountDTO(tag.getAccount()));
	}
	public TagDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	public List<MessageDTO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
	
	





}