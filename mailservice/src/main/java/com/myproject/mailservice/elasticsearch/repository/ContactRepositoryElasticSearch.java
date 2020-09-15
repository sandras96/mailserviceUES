package com.myproject.mailservice.elasticsearch.repository;


import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.myproject.mailservice.entity.Contact;


public interface ContactRepositoryElasticSearch extends ElasticsearchRepository<Contact, Integer>{

	List<Contact> findByfirstnameContainingAndEuserId(String firstName, Long userId);
	List<Contact> findBylastnameContainingAndEuserId(String lastName, Long userId);
	List<Contact> findBytextContainingAndEuserId(String text, Long userId);
	
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
	
/*	@Query("{\"simple_query_string\" : {\"query\": \"?0\" , \"fields\": [ \"firstName\", \"lastName\"], \"default_operator\": \"and\"}}")
	List<Contact> findPhrase(String q);*/
	
	@Query("{" +
		 " \"query\": {"+
		  "  \"match_phrase\": {"+
		   "   \"message\": "+
		      "\"this is a test\", " +
		   " }"+
		  "}"+
		"}")
	List<Contact> findPhrase(String q);
	
	/*
	 * @Query("{ \"query\" : { \"bool\" : { \"should\" : [ { \"query_string\" : { \"query\" : \"?\", \"fields\" : [ \"firstname\" ] } }, { \"query_string\" : { \"query\" : \"?\", \"fields\" : [ \"lastname\" ] } } ] } }}"
	 * ) List<Contact> findBoolQuery(String q1, String q2);
	 */
	
	@Query("{\"bool\" : {\"must\" : {\"query\" : \"?0\", \"fields\" :  [\"firstName\", \"lastName\", \"note\"]}}")
	List<Contact> findBoolQuery(String firstValue, String secondValue, String thirdValue);
}
