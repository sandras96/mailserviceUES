package com.myproject.mailservice.entity;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;



@Entity(name = "Folders")
@Table(name = "folders")
public class Folder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "folder_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "folder_name")
	private String name;
	
	
	@OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Message> messages = new HashSet<>();
	
	
	@OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Rule> rules = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parentFolder_id", referencedColumnName = "folder_id")
	private Folder parentFolder;
	
	
	@OneToMany(mappedBy = "parentFolder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Folder> childrenFolder;


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


	public Set<Message> getMessages() {
		return messages;
	}


	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}


	public Set<Rule> getRules() {
		return rules;
	}


	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public Folder getParentFolder() {
		return parentFolder;
	}


	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}


	public List<Folder> getChildrenFolder() {
		return childrenFolder;
	}


	public void setChildrenFolder(List<Folder> childrenFolder) {
		this.childrenFolder = childrenFolder;
	}


	public Folder(Long id, String name, Set<Message> messages, Set<Rule> rules, Account account, Folder parentFolder,
			List<Folder> childrenFolder) {
		super();
		this.id = id;
		this.name = name;
		this.messages = messages;
		this.rules = rules;
		this.account = account;
		this.parentFolder = parentFolder;
		this.childrenFolder = childrenFolder;
	}


	public Folder() {
		super();
	}


	@Override
	public String toString() {
		return "Folder [id=" + id + ", name=" + name + ", messages=" + messages + ", rules=" + rules + ", account="
				+ account + ", parentFolder=" + parentFolder + ", childrenFolder=" + childrenFolder + "]";
	}
	
	
	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Folder)) return false;
        return id != null && id.equals(((Folder) o).getId());
    }
 
    @Override
    public int hashCode() {
        return 31;
    }
	
	
	
	
	
}
