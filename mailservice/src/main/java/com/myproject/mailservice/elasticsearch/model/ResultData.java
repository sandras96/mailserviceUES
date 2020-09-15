package com.myproject.mailservice.elasticsearch.model;


public class ResultData {

	private String title;
	private String keywords;
	private String location;
	
	public ResultData() {
		super();
	}
	public ResultData(String title, String keywords, String location) {
		super();
		this.title = title;
		this.keywords = keywords;
		this.location = location;
	
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
