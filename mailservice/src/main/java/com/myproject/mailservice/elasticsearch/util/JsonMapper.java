package com.myproject.mailservice.elasticsearch.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonMapper {   
	
	
	
	private final static ObjectMapper mapper;

	static {
		 mapper = new ObjectMapper();
	}

   


	public static ObjectMapper getMapper() {
	    return mapper;
	}


}
