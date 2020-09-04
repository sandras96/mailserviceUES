package com.myproject.mailservice.entity;


import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity(name = "Photos")
@Table(name = "photos")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photo_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "photo_path", nullable = false)
	@Type(type = "text")
	private String path;
	
	
	@Column(name="type")
	private String type;
	
	@Column(name="name")
	private String name;
	
	@Lob
	@Column(name = "pic")
	private byte[] pic;

	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	private Contact contact;
	
	
	
	public Photo() {
		super();
	}


	public Photo(Long id, String path, String type, byte[] pic, Contact contact) {
		super();
		this.id = id;
		this.path = path;
		this.type = type;
		this.pic = pic;
		this.contact = contact;
	}


	public Photo(String name, String type, byte[] pic, Contact contact) {
		super();
		
		this.name = name;
		this.type = type;
		this.pic = pic;
		this.contact = contact;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
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





	public Contact getContact() {
		return contact;
	}










	public void setContact(Contact contact) {
		this.contact = contact;
	}










	public String getType() {
		return type;
	}



















	public void setType(String type) {
		this.type = type;
	}



















	public byte[] getPic() {
		return pic;
	}



















	public void setPic(byte[] pic) {
		this.pic = pic;
	}



















	@Override
	public String toString() {
		return "Photo [id=" + id + ", path=" + path + ", contact=" + contact + "]";
	}
	
	
	
	
	

}
