package com.myproject.mailservice.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.entity.User;


public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private List<AccountDTO> accounts;
	
	
	
	public UserDTO(Long id, String username, String password, String firstname, String lastname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.accounts = accounts;
	}

	public UserDTO(User user) {
		this(user.getId(), user.getUsername(),user.getPassword(),user.getFirstname(), user.getLastname());
	}
	
	public static List<AccountDTO> getAccoutnForUser(List<Account> acc){
		List<AccountDTO> ret=new ArrayList<>();
		for(Account a: acc) {
			ret.add(new AccountDTO(a));
		}
		return ret;
	}

	public UserDTO() {
		super();
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
	
	



	public List<AccountDTO> getAccounts() {
		return accounts;
	}



	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}



	
	

}
