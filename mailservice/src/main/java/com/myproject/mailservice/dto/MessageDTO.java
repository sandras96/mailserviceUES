package com.myproject.mailservice.dto;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Message;
import com.myproject.mailservice.entity.Tag;

@Document(indexName = "messagesDto")
public class MessageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private AccountDTO account;
	private FolderDTO folder;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private Timestamp dateTime;
	private String subject;
	private String content;
	private boolean unread;
	private ArrayList<TagDTO> tags;
	
	public MessageDTO() {
		super();
	}
	public MessageDTO(Long id, AccountDTO account, FolderDTO folder, String from, String to,
			String cc, String bcc, Timestamp dateTime, String subject, String content, boolean unread, ArrayList<TagDTO> tags) {
		super();
		this.id = id;
	
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
		this.tags = tags;
	}
	

	
	public MessageDTO(Message m){
		this(m.getId(), m.getFrom(), m.getTo(), m.getDateTime(), m.getSubject(), m.getContent(), m.getCc(), 
				m.getBcc(), new AccountDTO(m.getAccount()), new FolderDTO(), m.isUnread(), getTagsDTO(m.getTags()));
	}
	
	
	
	
	
	
	
	public MessageDTO(Long id, String from, String to, Timestamp dateTime, String subject, String content,
			String cc, String bcc, AccountDTO accountDTO, FolderDTO folderDTO, boolean unread,
			ArrayList<TagDTO> tagsDTO) {
		this.id = id;
		

		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	public FolderDTO getFolder() {
		return folder;
	}
	public void setFolder(FolderDTO folder) {
		this.folder = folder;
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
	
	public static ArrayList<TagDTO> getTagsDTO(List<Tag> tags){
		ArrayList<TagDTO> ret=new ArrayList<>();
		if(tags!=null) {
			for(Tag t: tags)
				//potencijalno ispraviti
				ret.add(null);
		}
		return ret;
	}
	
	
}