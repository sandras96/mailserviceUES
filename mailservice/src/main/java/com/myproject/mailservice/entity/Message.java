package com.myproject.mailservice.entity;



import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import javax.mail.Address;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity(name = "Message")
@Table(name = "message")
@Document(indexName = "messagesall", type = Message.TYPE_NAME, shards = 1, replicas = 0)
public class Message implements Serializable{
	
	/**
	 * 
	 */
	
	
	
//	public static final String INDEX_NAME = "messagesall";
	public static final String TYPE_NAME = "message";
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String analyzer = "{\"tokenizer\" : \"standard\",\"filter\" : [\"lowercase\"]}";
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id", nullable = false, unique = true)
	@Field(type = FieldType.Long, store = false)
	private Long id;
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "message_tag", 
			   joinColumns = @JoinColumn(name = "message_id"),
			   inverseJoinColumns = @JoinColumn(name = "tag_id")		
			)
	private List<Tag> tags = new ArrayList<>();
	
	
	
	@OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Attachment> attachments = new HashSet<>();
	

	
	
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	@Field(type = FieldType.Keyword, store = false)
	private Account account;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folder_id")
	private Folder folder;
	
	
	
	@Column(name = "from_message", nullable = false)
	@Type(type = "text")
	@Field(type = FieldType.Keyword, store = true )
	private String from;
	
	@Column(name = "to_message", nullable = false)
	@Type(type = "text")
	@Field(type = FieldType.Keyword, store = true)
	private String to;
	
	@Column(name = "cc_message")
	@Type(type = "text")
	private String cc;
	
	@Column(name = "bcc_message")
	@Type(type = "text")
	private String bcc;
	
	
	@Column(name = "dateTime", nullable = false)
	@Field(type = FieldType.Keyword, store = true)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
	private Timestamp dateTime;
	
	
	@Column(name = "subject_mess", length = 250)
	@Field(type = FieldType.Keyword, store = true)
	private String subject;
	
	@Column(name = "content")
	@Type(type = "text")
	@Field(type = FieldType.Keyword, store = false)
	private String content;
	
	@Column(name = "isUnread", nullable = false)
	private boolean unread;
	
	public Message() {
		super();
	}

	public Message(Long id, List<Tag> tags, Set<Attachment> attachments, Account account, Folder folder, String from,
			String to, String cc, String bcc, Timestamp dateTime, String subject, String content, boolean unread) {
		super();
		this.id = id;
		this.tags = tags;
		this.attachments = attachments;
		this.account = account;
		this.folder = folder;
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
	}






	public Message(String from, String to, String cc, String bcc, Timestamp dateTime, String subject,
			String content, boolean unread, Account account) {
		super();
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
		this.account = account;
	}






	/*
	 * public Message(String description, String text, Address[] from, String
	 * subject, Date sentDate, Account account) { super(); Timestamp ts = new
	 * Timestamp(sentDate.getTime()); this.from = from.toString(); this.cc =
	 * description; this.subject = subject; this.content = text; this.dateTime = ts;
	 * this.to = "djapic.andrej@gmail.com"; this.account = account; }
	 */






	public void addTag(Tag tag) {
		tags.add(tag);
		tag.getMessages().add(this);
	}
	
    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getMessages().remove(this);
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        return id != null && id.equals(((Message) o).getId());
    }
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", tags=" + tags + ", from=" + from + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc
				+ ", dateTime=" + dateTime + "]";
	}
	
	
}
