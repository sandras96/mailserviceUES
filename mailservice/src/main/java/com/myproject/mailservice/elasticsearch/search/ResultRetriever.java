package com.myproject.mailservice.elasticsearch.search;


import java.util.ArrayList;

import java.util.List;

import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.Query;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.elasticsearch.model.RequiredHighlight;
import com.myproject.mailservice.elasticsearch.model.ResultData;
import com.myproject.mailservice.elasticsearch.repository.ContactRepositoryElasticSearch;
import com.myproject.mailservice.entity.Contact;




@Service
public class ResultRetriever {

	@Autowired
	private ContactRepositoryElasticSearch contactRepository;

	@Autowired
	private GeneralSearchService generalSearchService;
	public ResultRetriever(){
	}

	public  List<ResultData> getResults(org.elasticsearch.index.query.QueryBuilder query, List<RequiredHighlight> requiredHighlights) {
		if (query == null) {
			return null;
		}
		System.out.println("kveri jeeee " + query + requiredHighlights.size());
		List<ResultData> results = new ArrayList<ResultData>();
		System.out.println("sta vratiss" + contactRepository.search(query));
		     
	    for (Contact contact : contactRepository.search(query)) {
	    	System.out.println("hejhejjj" + contactRepository.search(query).toString());
	        results.add(new ResultData(contact.getFirstname(), contact.getLastname(), contact.getNote(), ""));
		}  
	
		return results;
	}
	
//	public List<ResultData> getResultsMessages(org.elasticsearch.index.query.QueryBuilder query,
//		List<RequiredHighlight> requiredHighlights) {
//		if (query == null) {
//			return null;
//		}
//
//		List<ResultData> results = new ArrayList<ResultData>();
//		     
//	    for (Message m : repository.search(query)) {
//	    	System.out.println(repository.search(query).toString());
//	        results.add(new ResultData(indexUnit.getFirstName(), indexUnit.getLastName(), indexUnit.getNote(), ""));
//		}
//		       
//	
//		return results;
//		}
	
		
		
}
