package com.myproject.mailservice.elasticsearch.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class ElasticIndexer {
	
	
    private final ObjectMapper mapper = com.myproject.mailservice.elasticsearch.util.JsonMapper.getMapper();

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final RestClient client;
	
	private static final String MESSAGE_INDEX = "messages";
	
//	Konstruktor za otvaranje klijenta
	public ElasticIndexer() {
		System.out.println("usao u clienta");
		this.client = RestClient.builder(
			    new HttpHost("localhost", 9200, "http")).build();
		/*
		 * this.client = new RestHighLevelClient( RestClient.builder(
		 * 
		 * new HttpHost("localhost", 9200, "http") ));
		 */
		
	}
	
	
	
	/*
	 * public IndexResponse index(BaseIndexingObject document) throws IOException {
	 * final String json = mapper.writeValueAsString(document);
	 * System.out.println("klient json " + json); final IndexRequest request = new
	 * IndexRequest(MESSAGE_INDEX) .id(document.getId()) .source(json,
	 * XContentType.JSON);
	 * 
	 * final IndexResponse indexResponse = client.index(request,
	 * RequestOptions.DEFAULT);
	 * 
	 * return indexResponse; }
	 * 
	 * 
	 * 
	 * public void indexAll(List<? extends BaseIndexingObject> documents) throws
	 * IOException { final List<IndexRequest> indexRequests = new ArrayList<>(); for
	 * (BaseIndexingObject document: documents) { final String json =
	 * mapper.writeValueAsString(document); indexRequests.add( new
	 * IndexRequest(MESSAGE_INDEX) .id(document.getId()) .source(json,
	 * XContentType.JSON) ); }
	 * 
	 * final BulkRequest request = new BulkRequest();
	 * indexRequests.forEach(request::add);
	 * 
	 * client.bulk(request, RequestOptions.DEFAULT);
	 * logger.info("bulk successfully indexed"); }
	 * 
	 * 
	 * 
	 * public void clearIndex() throws IOException { try {
	 * client.indices().delete(new DeleteIndexRequest(MESSAGE_INDEX),
	 * RequestOptions.DEFAULT); } catch (ElasticsearchStatusException e) {
	 * logger.warn("Index already deleted..."); } }
	 * 
	 * 
	 * 
	 * 
	 * public void updateMessage(final BaseIndexingObject document) throws
	 * IOException { final String json = mapper.writeValueAsString(document);
	 * 
	 * final UpdateRequest request = new UpdateRequest(MESSAGE_INDEX,
	 * document.getId(), json) .doc(json, XContentType.JSON);
	 * 
	 * client.update(request, RequestOptions.DEFAULT); }
	 * 
	 * 
	 * public void deleteMessage(final String id) throws IOException { final
	 * DeleteRequest deleteRequest = new DeleteRequest() .index(MESSAGE_INDEX)
	 * .id(id); client.delete(deleteRequest, RequestOptions.DEFAULT); }
	 */

    
    private void destroyOnClose() {
        logger.info("Closing ElasticSearch client connection......");
        try {
            this.client.close();
            logger.info("ElasticSearch client connection closed.");
        } catch (IOException e) {
        	logger.error("Encountered error while closing ElasticSearch client connection.");
            e.printStackTrace();
        }

    }

}
