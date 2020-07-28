package com.myproject.mailservice.dto;


import java.io.Serializable;

public class RuleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private int condition;
	private String value;
	private int operation;
	private FolderDTO folder;
	
	
	public RuleDTO() {
		super();
	}
	public RuleDTO(Long id, int condition, String value, int operation, FolderDTO folder) {
		super();
		this.id = id;
		this.condition = condition;
		this.value = value;
		this.operation = operation;
		this.folder = folder;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public FolderDTO getFolder() {
		return folder;
	}
	public void setFolder(FolderDTO folder) {
		this.folder = folder;
	}
	
	
	
	
}
