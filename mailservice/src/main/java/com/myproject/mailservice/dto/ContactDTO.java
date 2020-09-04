package com.myproject.mailservice.dto;


import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.elasticsearch.annotations.Document;

import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.User;




public class ContactDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2125141740501605009L;
	
	private Long id;
	private String firstname;
	private String lastname;
	private String displayName;
	private String email;
	private String text;
	private UserDTO user;
	
	
	
	
	
	
	public ContactDTO() {
		super();
	}




	public ContactDTO(Long id, String firstname, String lastname, String displayName, String email, String text,
			UserDTO user) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.displayName = displayName;
		this.email = email;
		this.text = text;
		this.user = user;
	}

	
	public ContactDTO(Long id, 
			String firstname, 
			String lastname, 
			String displayName, 
			String email, 
			String text, 
			UserDTO user,
			ArrayList<MessageDTO> messagesTo, 
			ArrayList<MessageDTO> messagesFrom, 
			ArrayList<MessageDTO> messagesCc,  
			ArrayList<MessageDTO> messagesBcc) {
		
		super();
		
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.displayName = displayName;
		this.email = email;
		this.text = text;
		this.user = user;
	}






	public ContactDTO(Contact co) { 
		this(co.getId(), co.getFirstname(), co.getLastname(), co.getDisplayName(), co.getEmail(), co.getText(), new UserDTO(co.getEuser()));
		// TODO Auto-generated constructor stub
		
	}








	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getFirstname() {
		return firstname;
	}




	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}




	public String getLastname() {
		return lastname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}




	public String getDisplayName() {
		return displayName;
	}




	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getText() {
		return text;
	}




	public void setText(String text) {
		this.text = text;
	}




	public UserDTO getUser() {
		return user;
	}




	public void setUser(UserDTO user) {
		this.user = user;
	}




	@Override
	public String toString() {
		return "ContactDTO [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", displayName="
				+ displayName + ", email=" + email + ", text=" + text + ", user=" + user + "]";
	}
	
	
	
	
	
}
