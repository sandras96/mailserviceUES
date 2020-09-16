package com.myproject.mailservice.entity;



import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Attachment")
@Table(name = "attachment")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Document(indexName = "attachments")
public class Attachment {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attachment_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "attachment_path")
	@Type(type = "text")
	private String path;
	
	
	@Column(name = "attachment_mime")
	private String mime;
	
	
	@Column(name = "attachment_name")
	private String name;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	
	@JoinColumn(name = "message_id")
	@JsonIgnore
	private Message message;
	


	public Attachment() {
		super();
	}


	public Attachment(Long id, String path, String mime, String name, Message message) {
		super();
		this.id = id;
		this.path = path;
		this.mime = mime;
		this.name = name;
		this.message = message;
	}
	
	public Attachment(Long id, String path, String mime, String name) {
		this.id = id;
		this.path = path;
		this.mime = mime;
		this.name = name;
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


	public String getMime() {
		return mime;
	}


	public void setMime(String mime) {
		this.mime = mime;
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


	
	
	
	

}
