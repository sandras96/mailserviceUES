package com.myproject.mailservice.elasticsearch.handlers;

import java.io.File;

import com.myproject.mailservice.elasticsearch.model.IndexUnit;


public abstract class DocumentHandler {

	public abstract IndexUnit getIndexUnit(File file);
	public abstract String getText(File file);
	
}
