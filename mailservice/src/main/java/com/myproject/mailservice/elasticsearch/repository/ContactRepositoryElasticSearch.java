package com.myproject.mailservice.elasticsearch.repository;


import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.myproject.mailservice.entity.Contact;


public interface ContactRepositoryElasticSearch extends ElasticsearchRepository<Contact, Integer>{

	List<Contact> findByfirstname(String firstName);
	List<Contact> findBylastname(String lastName);
	
	@Query("{" + 
			"    \"query\": {" + 
			"        \"multi_match\": {" + 
			"            \"query\": " + 
			"                \"mag\", " + 
			"                \"fields\": [" + 
			"                    \"firstname\", " + 
			"                    \"lastname\", " + 
			"                    \"note\"" + 
			"                ], " + 
			"                \"fuzziness\": " + 
			"                    \"AUTO\"" + 
			"        }" + 
			"    }" + 
			"}")
	List<Contact> findFuzzy(String q);
	
	@Query("{\"simple_query_string\" : {\"query\": \"?0\" , \"fields\": [ \"firstName\", \"lastName\"], \"default_operator\": \"and\"}}")
	List<Contact> findPhrase(String q);
	
	
}
