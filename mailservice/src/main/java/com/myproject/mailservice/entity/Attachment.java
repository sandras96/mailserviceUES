package com.myproject.mailservice.entity;



import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Attachment")
@Table(name = "attachment")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Attachment {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attachment_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "attachment_path")
	@Type(type = "text")
	private String path;
	
	
	@Column(name = "attachment_mime")
	private String MIME;
	
	
	@Column(name = "attachment_name")
	private String name;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id")
	private Message message;
	


	public Attachment() {
		super();
	}


	public Attachment(Long id, String path, String mIME, String name, Message message) {
		super();
		this.id = id;
		this.path = path;
		MIME = mIME;
		this.name = name;
		this.message = message;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getMIME() {
		return MIME;
	}


	public void setMIME(String mIME) {
		MIME = mIME;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "Attachment [id=" + id + ", path=" + path + ", MIME=" + MIME + ", name=" + name + ", message=" + message
				+ "]";
	}
	
	
	
	

}
