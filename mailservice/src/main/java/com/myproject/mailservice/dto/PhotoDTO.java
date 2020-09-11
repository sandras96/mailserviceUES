package com.myproject.mailservice.dto;


import java.io.Serializable;

import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Photo;

public class PhotoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String path;
	private String name;
	private byte[] pic;
	private ContactDTO contact;
	
	
	
	
	public PhotoDTO(Long id, String path, ContactDTO contact) {
		super();
		this.id = id;
		this.path = path;
		this.contact = contact;
	}
	
	
	
	
	public PhotoDTO(Long id, String path, String name, byte[] pic, ContactDTO contact) {
		super();
		this.id = id;
		this.path = path;
		this.name = name;
		this.pic = pic;
		this.contact = contact;
	}




	public PhotoDTO() {
		super();
	}
	
	public PhotoDTO(Photo photo) {
		this(photo.getId(), photo.getName(), photo.getPath(), photo.getPic(), new ContactDTO(photo.getContact()));
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
	public ContactDTO getContact() {
		return contact;
	}
	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public byte[] getPic() {
		return pic;
	}


	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	
	
	
}
