package com.myproject.mailservice.elasticsearch.search;

import java.util.ArrayList;

import java.util.List;

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

	public ResultRetriever(){
	}

	public List<ResultData> getResults(org.elasticsearch.index.query.QueryBuilder query,
		List<RequiredHighlight> requiredHighlights) {
		if (query == null) {
			return null;
		}

		List<ResultData> results = new ArrayList<ResultData>();
		     System.out.println("Sta vratisssss" + contactRepository.search(query));
		     
	    for (Contact indexUnit : contactRepository.search(query)) {
	    	System.out.println(contactRepository.search(query).toString());
	        results.add(new ResultData(indexUnit.getFirstname(), indexUnit.getDisplayName(), indexUnit.getNote()));
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
	
	/*
	 * protected DocumentHandler getHandler(String fileName){
	 * if(fileName.endsWith(".pdf")){ return new PdfHandler(); } else { return null;
	 * } }
	 */
		
		
}
