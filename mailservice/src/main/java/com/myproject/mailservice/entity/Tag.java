package com.myproject.mailservice.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity(name = "Tag")
@Table(name = "tag")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "tag_name", nullable = false, length = 100)
	private String name;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User euser;
	
	
	@ManyToMany(mappedBy = "tags")
	private List<Message> messages = new ArrayList<>();

	
	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
	
	

	public Tag() {
		super();
	}


	public Tag(Long id, String name, User euser, List<Message> messages) {
		super();
		this.id = id;
		this.name = name;
		this.euser = euser;
		this.messages = messages;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public User getEuser() {
		return euser;
	}


	public void setEuser(User euser) {
		this.euser = euser;
	}


	public List<Message> getMessages() {
		return messages;
	}


	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", euser=" + euser + ", messages=" + messages + "]";
	}
	
	
	
	
}
