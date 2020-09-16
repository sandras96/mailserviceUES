package com.myproject.mailservice.dto;


import java.io.Serializable;

import com.myproject.mailservice.entity.Attachment;

public class AttachmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private Long id;
	private String path;
	private String mime;
	private String name;
	private MessageDTO message;
	
	
		
	
	public AttachmentDTO() {
		super();
	}

	public AttachmentDTO(Attachment a) {
		this(a.getId(), a.getMime(), a.getName(), a.getPath());
	}
	public AttachmentDTO(Long id, String mime, String name, String path ) {
		super();
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
	public MessageDTO getMessage() {
		return message;
	}
	public void setMessage(MessageDTO message) {
		this.message = message;
	}
	
	
	
	
}
