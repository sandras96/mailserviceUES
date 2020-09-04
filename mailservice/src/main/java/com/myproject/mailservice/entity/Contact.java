package com.myproject.mailservice.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity(name = "Contact")
@Table(name = "contact")
@Document(indexName = Contact.INDEX_NAME, type = Contact.TYPE_NAME, shards = 1, replicas = 0)
public class Contact implements Serializable {

	
	public static final String INDEX_NAME = "contactsadmin1";
	public static final String TYPE_NAME = "contact";
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
//	public static final String analyzer = "{\"tokenizer\" : \"standard\",\"filter\" : [\"lowercase\"]}";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id", unique = true, nullable = false)
	@Field(type = FieldType.Long, store = false )
	private Long id;
	
	
	@Field(type = FieldType.Keyword,  store = true)
	@Column(name = "contact_firstname", length = 100)
	private String firstname;
	
	
	@Field(type = FieldType.Keyword, store = true)
	@Column(name = "contact_lastname", length = 100)
	private String lastname;
	
	@Column(name = "contact_displayName", length = 100, nullable = false)
	@Field(type = FieldType.Keyword,  store = true)
	private String displayName;
	
	@Field(type = FieldType.Keyword,  store = true)
	@Column(name = "contact_email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "contact_note", length = 100, nullable = false)
	@Field(type = FieldType.Keyword,  store = true)
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User euser;
	
	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Photo> photos = new HashSet<>();
	
	
	public Contact() {
		super();
	}

	
	public Contact(Long id, String firstname, String lastname, String displayName, String email, String text,
			User euser, Set<Photo> photos) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.displayName = displayName;
		this.email = email;
		this.text = text;
		this.euser = euser;
		this.photos = photos;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getEuser() {
		return euser;
	}

	public void setEuser(User euser) {
		this.euser = euser;
	}


	public Set<Photo> getPhotos() {
		return photos;
	}


	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}


	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", displayName="
				+ displayName + ", email=" + email + ", text=" + text + ", euser=" + euser + ", photos=" + photos + "]";
	}

	   @Override
	   @JsonIgnore
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Contact )) return false;
	        return id != null && id.equals(((Contact) o).getId());
	    }
	
	   
	

	@JsonIgnore
	public int hashCode() {
		return displayName.hashCode();
	}


	public String getNote() {
		// TODO Auto-generated method stub
		return "note";
	}


	
	
	
}
