package com.myproject.mailservice.elasticsearch.model;


public class RequiredHighlight {
	
	private String fieldName;
	private String value;
	
	public RequiredHighlight() {
		super();
	}
	
	public RequiredHighlight(String fieldName, String value) {
		super();
		this.fieldName = fieldName;
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
}
