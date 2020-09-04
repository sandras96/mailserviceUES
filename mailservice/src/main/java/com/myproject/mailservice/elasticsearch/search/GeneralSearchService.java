package com.myproject.mailservice.elasticsearch.search;


import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.dto.MessageDTO;
import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Message;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.elasticsearch.common.unit.Fuzziness;


@Service
public class GeneralSearchService {
	
	@Autowired
	private ElasticsearchRestTemplate elasticsearchTemplate;
	 
	 
    public List<Contact> searchContacts(String query) {
    	return elasticsearchTemplate.queryForList(
    		contactQuery(query), 
            Contact.class
        );    			
    }
    
    
    public NativeSearchQuery contactQuery(String query) {
        MultiMatchQueryBuilder contactMultiMatchQuery = QueryBuilders.multiMatchQuery(query)
            .field("firstname")
            .field("lastname")
            .operator(Operator.OR)
            .fuzziness(Fuzziness.AUTO)
            .prefixLength(3);

        return new NativeSearchQueryBuilder().withQuery(contactMultiMatchQuery).build();
    }
    
	
	  public List<Message> searchMessages(String query) { return
	  elasticsearchTemplate.queryForList( messageQuery(query), Message.class );
	  }
	  
	  public NativeSearchQuery messageQuery(String query) { MultiMatchQueryBuilder
	  messageMultiMatchQuery = QueryBuilders.multiMatchQuery(query) .field("from")
	  .field("to") .field("subject") .operator(Operator.OR)
	  .fuzziness(Fuzziness.AUTO) .prefixLength(3);
	  
	  return new
	  NativeSearchQueryBuilder().withQuery(messageMultiMatchQuery).build(); }
	 
}
