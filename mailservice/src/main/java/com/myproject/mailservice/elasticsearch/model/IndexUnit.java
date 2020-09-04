package com.myproject.mailservice.elasticsearch.model;


import org.apache.lucene.document.TextField;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(indexName = IndexUnit.INDEX_NAME, type = IndexUnit.TYPE_NAME, shards = 1, replicas = 0)
public class IndexUnit {
	
	public static final String INDEX_NAME = "digitallibrary";
	public static final String TYPE_NAME = "book";

	public static final String DATE_PATTERN = "yyyy-MM-dd";

	@Field(type = FieldType.Keyword, store = true)
	private String text;
	
	@Field(type = FieldType.Keyword, store = true)
	private String title;
	
	@Field(type = FieldType.Keyword,store = true)
	private String keywords;
	
	@Id
	@Field(type = FieldType.Keyword, store = true)
	private String filename;
	
	@Field(type = FieldType.Keyword, store = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
	
	private String filedate;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiledate() {
		return filedate;
	}
	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}
	public static String getIndexName() {
		return INDEX_NAME;
	}
	public static String getTypeName() {
		return TYPE_NAME;
	}
	public static String getDatePattern() {
		return DATE_PATTERN;
	}
	@Override
	public String toString() {
		return "IndexUnit [text=" + text + ", title=" + title + ", keywords=" + keywords + ", filename=" + filename
				+ ", filedate=" + filedate + "]";
	}
	

}
