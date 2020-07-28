package com.myproject.mailservice.dto;


import java.io.Serializable;

public class AttachmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private Long id;
	private String path;
	private String MIME;
	private String name;
	private MessageDTO message;
	
	
		
	
	public AttachmentDTO() {
		super();
	}

	public AttachmentDTO(Long id, String path, String mIME, String name, MessageDTO message) {
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
	public MessageDTO getMessage() {
		return message;
	}
	public void setMessage(MessageDTO message) {
		this.message = message;
	}
	
	
	
	
}
