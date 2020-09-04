package com.myproject.mailservice.elasticsearch.util;

import javax.validation.constraints.NotBlank;

public abstract class BaseIndexingObject {

	   @NotBlank
	    protected String id;

		public String getId() {
			return id;
		}
	    
}
