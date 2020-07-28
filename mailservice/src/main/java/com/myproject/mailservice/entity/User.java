package com.myproject.mailservice.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity(name = "eUser")
@Table(name = "euser")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable/* , UserDetails */{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, unique = true)
	private Long id;
	
	
	
	@OneToMany(mappedBy = "euser", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Account> accounts;
	
	
	@OneToMany(mappedBy = "euser", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Contact> contacts;
	
	
	@OneToMany(mappedBy = "euser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tag> tags;
	
	@Column(name = "user_username", nullable = false, unique = true)
	private String username;
	
	
	@Column(name = "user_password", nullable = false)
	private String password;
	
	
	@Column(name = "user_firstname", nullable = false, unique = true)
	private String firstname;
	
	@Column(name = "user_lastname", nullable = true)
	private String lastname;
	
	
	
	
	

	public User() {
		super();
	}



	public User(String firstname, String username, String password) {
		super();
		this.firstname = firstname;
		this.username = username;
		this.password = password;
	}









	public User(Long id, List<Account> accounts, List<Contact> contacts, List<Tag> tags, String username, String password,
			String firstname, String lastname) {
		super();
		this.id = id;
		this.accounts = accounts;
		this.contacts = contacts;
		this.tags = tags;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

















	public User(String firstname, String lastname, String username, String password) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}







	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public String getUsername() {
		return username;
	}






	public void setUsername(String username) {
		this.username = username;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
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






	public List<Account> getAccounts() {
		return accounts;
	}



	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	public List<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}



	public List<Tag> getTags() {
		return tags;
	}


	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}






    
	/*
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * return this.userAuthorities; }
	 * 
	 * 
	 * 
	 * @Override public boolean isAccountNonExpired() { return true; }
	 * 
	 * 
	 * @Override public boolean isAccountNonLocked() { return true; }
	 * 
	 * 
	 * @Override public boolean isCredentialsNonExpired() { return true; }
	 * 
	 * 
	 * @Override public boolean isEnabled() { return true; }
	 */
	
	
	
	
	

}
