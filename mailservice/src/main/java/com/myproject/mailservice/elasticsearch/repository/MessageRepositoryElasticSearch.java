package com.myproject.mailservice.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.myproject.mailservice.entity.Message;

public interface MessageRepositoryElasticSearch extends ElasticsearchRepository<Message, Integer> {
	
	List<Message> findBySubject(String subject);
//	List<Message> findBySender(String from);
	List<Message> findByFrom(String from);
//	List<Message> findByContent(String content);
	
	@Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"subject\", \"from\", \"to\", \"content\"], \"fuzziness\": \"AUTO\"}}")
	List<Message> findFuzzyMessage(String q);
	
	@Query("{\"bool\" : {\"must\" : {\"query\" : \"?0\", \"fields\" :  [\"subject\", \"from\", \"to\", \"content\"]}}")
	List<Message> findBoolQuery(String firstValue, String secondValue, String thirdValue, String fourthValue);
	
	@Query("{\"simple_query_string\" : {\"query\": \"?0\" , \"fields\": [\"subject\", \"from\", \"to\", \"content\"], \"default_operator\": \"and\"}}")
	List<Message> findPhraseMessage(String q);
}
