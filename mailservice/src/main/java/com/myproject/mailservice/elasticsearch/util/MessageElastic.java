package com.myproject.mailservice.elasticsearch.util;


import java.util.List;

import com.myproject.mailservice.entity.Message;


public class MessageElastic extends BaseIndexingObject {
	
	
	
	private List<String> tags;
	private List<String> attachments;
	private String account;
	//private String folder;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String content;
	private String unread;
	private String dateTime;
	public List<String> getTags() {
		return tags;
	}
	public List<String> getAttachments() {
		return attachments;
	}
	public String getAccount() {
		return account;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getCc() {
		return cc;
	}
	public String getBcc() {
		return bcc;
	}
	public String getSubject() {
		return subject;
	}
	public String getContent() {
		return content;
	}
	public String getUnread() {
		return unread;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	
	
	public MessageElastic(Message message) {
		this.id = String.valueOf(message.getId());
		//this.account = message.getAccount().getUsername();
		this.from = message.getFrom();
		this.to = message.getTo();
		this.cc = message.getCc();
		this.bcc = message.getBcc();
		this.subject = message.getSubject();
		this.content = message.getContent();
		this.dateTime = message.getDateTime().toString();
		this.unread = Boolean.toString(message.isUnread());
	}
	@Override
	public String toString() {
		return "MessageElastic [tags=" + tags + ", attachments=" + attachments + ", account=" + account + ", from="
				+ from + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject=" + subject + ", content=" + content
				+ ", unread=" + unread + ", dateTime=" + dateTime + "]";
	}

	
	
}
