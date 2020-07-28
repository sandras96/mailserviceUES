package com.myproject.mailservice.entity;


import javax.persistence.*;


@Entity(name = "Rule")
@Table(name = "rule")
public class Rule {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rule_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "rule_condition", nullable = false)
	private int condition;
	
	@Column(name = "rule_value", nullable = false)
	private String value;
	
	@Column(name = "rule_operation", nullable = false)
	private int operation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folder_id")
	private Folder folder;
	
	
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
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	public Rule(Long id, int condition, String value, int operation, Folder folder) {
		super();
		this.id = id;
		this.condition = condition;
		this.value = value;
		this.operation = operation;
		this.folder = folder;
	}
	
	
	public Rule() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Rule [id=" + id + ", condition=" + condition + ", value=" + value + ", operation=" + operation
				+ ", folder=" + folder + "]";
	}
	
	
	
	
	

}
