package com.myproject.mailservice.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity(name = "Accounts")
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id", nullable = false, unique = true)
	private Long id;
	
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Message> messages;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Folder> folders;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User euser;
	
	@Column(name = "smtpAddress", nullable = false)
	private String smtpAddress;
	
	@Column(name = "smtpPort", nullable = false)
	private int smtpPort;
	
	@Column(name = "inServerType", nullable = false)
	private int inServerType;
	
	@Column(name = "inServerAddress", nullable = false)
	private String inServerAddress;
	
	@Column(name = "inServerPort", nullable = false)
	private int inServerPort;
	
	@Column(name = "account_username", unique = true)
	private String username;
	
	@Column(name = "account_password")
	private String password;
	
	@Column(name = "displayName", nullable = false)
	private String displayName;
	
	
	
	   


	public Account() {
		super();
	}



	public Account(Long id, List<Message> messages, List<Folder> folders, User euser, String smtpAddress, int smtpPort,
			int inServerType, String inServerAddress, int inServerPort, String username, String password,
			String displayName) {
		super();
		this.id = id;
		this.messages = messages;
		this.folders = folders;
		this.euser = euser;
		this.smtpAddress = smtpAddress;
		this.smtpPort = smtpPort;
		this.inServerType = inServerType;
		this.inServerAddress = inServerAddress;
		this.inServerPort = inServerPort;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return euser;
	}

	public void setUser(User euser) {
		this.euser = euser;
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

	public int getInServerType() {
		return inServerType;
	}

	public void setInServerType(int inServerType) {
		this.inServerType = inServerType;
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
	

	public List<Message> getMessages() {
		return messages;
	}


	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public List<Folder> getFolders() {
		return folders;
	}


	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}


	public User getEuser() {
		return euser;
	}



	public void setEuser(User euser) {
		this.euser = euser;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", user=" + euser + ", smtpAddress=" + smtpAddress + ", smtpPort=" + smtpPort
				+ ", inServerType=" + inServerType + ", inServerAddress=" + inServerAddress + ", inServerPort="
				+ inServerPort + ", username=" + username + ", password=" + password + ", displayName=" + displayName
				+ "]";
	}
	
}
