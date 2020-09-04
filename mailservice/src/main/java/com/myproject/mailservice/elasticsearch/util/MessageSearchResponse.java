package com.myproject.mailservice.elasticsearch.util;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MessageSearchResponse  {
	
private Long id;
//private List<MessageDTO> messages;
private String account;



private List<String> suggestions;

@Setter
private List<MessageSearchResponse.Message> messages;




@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public static final class Message{
	@Positive
	private long id;
	
	@NotBlank
	@Size(min = 1, max = 200)
	private String subject;
	
	@Size(min = 2 , max = 100)
	private String account;
	
	private String cc;
	private String bcc;
	private String content;
	private String to;
	private String from;
	private String timeStamp;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	
}










public MessageSearchResponse(List<MessageSearchResponse.Message> result) {
	//this.suggestions = suggestions;
	this.messages = result;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}




public void setAccount(String account) {
	this.account = account;
}





}



