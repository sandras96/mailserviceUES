package com.myproject.mailservice.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Message;
import com.myproject.mailservice.entity.User;


public class AccountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private UserDTO user;
	private String smtpAddress;
	private int inServerType;
	private int smtpPort;
	private String inServerAddress;
	private int inServerPort;
	private String username;
	private String password;
	private String displayName;
	
	

	
	public AccountDTO() {
		super();
	}
	public AccountDTO(Long id, UserDTO user, String smtpAddress, int inServerType, int smtpPort, String inServerAddress,
			int inServerPort, String username, String password, String displayName) {
		super();
		this.id = id;
		this.user = user;
		this.smtpAddress = smtpAddress;
		this.inServerType = inServerType;
		this.smtpPort = smtpPort;
		this.inServerAddress = inServerAddress;
		this.inServerPort = inServerPort;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
	}
	
	public AccountDTO(Long id, String inServerAddress, String username, String password, int smtpPort, 
			String smtpAddress, String displayName, UserDTO user, int inServerType, int inServerPort) {
		this.id = id;
		this.inServerAddress = inServerAddress;
		this.username = username;
		this.password = password;
		this.smtpPort = smtpPort;
		this.smtpAddress = smtpAddress;
		this.displayName = displayName;
		this.user = user;
		this.inServerType = inServerType;
		this.inServerPort = inServerPort;
		
	}
	

	public AccountDTO(Account a){
		this(a.getId(),a.getInServerAddress(), a.getUsername(), a.getPassword(), a.getSmtpPort(),
				a.getSmtpAddress(), a.getDisplayName(), new UserDTO(a.getEuser()), a.getInServerType(), a.getInServerType());
	}
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public String getSmtpAddress() {
		return smtpAddress;
	}
	public void setSmtpAddress(String smtpAddress) {
		this.smtpAddress = smtpAddress;
	}
	public int getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getInServerAddress() {
		return inServerAddress;
	}
	public void setInServerAddress(String inServerAddress) {
		this.inServerAddress = inServerAddress;
	}
	public int getInServerPort() {
		return inServerPort;
	}
	public void setInServerPort(int inServerPort) {
		this.inServerPort = inServerPort;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getInServerType() {
		return inServerType;
	}
	public void setInServerType(int inServerType) {
		this.inServerType = inServerType;
	}
	
	
}
