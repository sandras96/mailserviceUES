package com.myproject.mailservice.dto;


import java.io.Serializable;
import java.util.List;

public class FolderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8230387467728832524L;

	
	private Long id;
	private String name;
	private AccountDTO account;
	private FolderDTO parentFolder;
	private List<FolderDTO> ChildrenFolders;
	
	
	
	
	public FolderDTO(Long id, String name, AccountDTO account, FolderDTO parentFolder,
			List<FolderDTO> childrenFolders) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.parentFolder = parentFolder;
		ChildrenFolders = childrenFolders;
	}
	
	
	
	
	
	
	
	public FolderDTO() {
		super();
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
	public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	public FolderDTO getParentFolder() {
		return parentFolder;
	}
	
	public Long getParentFolderId() {
		return parentFolder.getId();
	}
	
	public void setParentFolder(FolderDTO parentFolder) {
		this.parentFolder = parentFolder;
	}
	public List<FolderDTO> getChildrenFolders() {
		return ChildrenFolders;
	}
	public void setChildrenFolders(List<FolderDTO> childrenFolders) {
		ChildrenFolders = childrenFolders;
	}
	
	
	
	
	
}
