/*package com.myproject.mailservice.elasticsearch.search;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myproject.mailservice.elasticsearch.handlers.DocumentHandler;
import com.myproject.mailservice.elasticsearch.handlers.PDFHandler;
import com.myproject.mailservice.elasticsearch.model.RequiredHighlight;
import com.myproject.mailservice.elasticsearch.model.ResultData;
import com.myproject.mailservice.elasticsearch.repository.ContactRepositoryElasticSearch;
import com.myproject.mailservice.elasticsearch.util.SerbianAnalyzer;
import com.myproject.mailservice.entity.Contact;



public class ResultRetriever1 {
	
	private TopScoreDocCollector collector;
	private static int maxHits = 10;
	
	@Autowired
	private static ContactRepositoryElasticSearch contactRepository;
	
	public ResultRetriever1(){
		collector=TopScoreDocCollector.create(10);
	}
	
	public static void setMaxHits(int maxHits) {
		ResultRetriever1.maxHits = maxHits;
	}

	public static int getMaxHits() {
		return ResultRetriever1.maxHits;
	}

	public static List<ResultData> getResults(Query builder,
			List<RequiredHighlight> requiredHighlights) {
		if (builder == null) {
			return null;
		}
		try {
			Directory indexDir = new SimpleFSDirectory(FileSystems.getDefault().getPath(ResourceBundle
					.getBundle("application").getString("index")));
			DirectoryReader reader = DirectoryReader.open(indexDir);
			IndexSearcher is = new IndexSearcher(reader);
			TopScoreDocCollector collector = TopScoreDocCollector.create(
					maxHits);

			List<ResultData> results = new ArrayList<ResultData>();

			is.search(builder, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			ResultData rd;
			Document doc;
			Highlighter hl;
			SerbianAnalyzer sa = new SerbianAnalyzer();
			
			for (ScoreDoc sd : hits) {
				doc = is.doc(sd.doc);
				String[] allKeywords = doc.getValues("keyword");
				String keywords = "";
				for (String keyword : allKeywords) {
					keywords += keyword.trim() + " ";
				}
				keywords = keywords.trim();
				String title = doc.get("firstName");
				String location = doc.get("lastName");
				String author =  doc.get("note");
				String highlight = "";
				for (RequiredHighlight rh : requiredHighlights) {
					hl = new Highlighter(new QueryScorer(builder, reader, rh.getFieldName()));
					try{
						highlight += hl.getBestFragment(sa, rh.getFieldName(),
								"" + getDocumentText(location));
					}catch (InvalidTokenOffsetsException e) {
						//throw new IllegalArgumentException("Unable to make highlight");
					}
				}
				rd = new ResultData(title,author, keywords, location);
				results.add(rd);
			}
			reader.close();
			return results;

		} catch (IOException e) {
			throw new IllegalArgumentException(
					"U prosledjenom direktorijumu ne postoje indeksi ili je direktorijum zakljucan");
		} 
	}
	
	public String printSearchResults(Query query, File indexDir){
		StringBuilder retVal = new StringBuilder();
		try{
			Directory fsDir=new SimpleFSDirectory(FileSystems.getDefault().getPath(indexDir.getAbsolutePath()));
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			IndexSearcher is = new IndexSearcher(ireader);
			is.search(query, collector);
			
			ScoreDoc[] hits=collector.topDocs().scoreDocs;
			System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId=hits[i].doc;
				Document doc = is.doc(docId);
				retVal.append("\t"+doc.get("title")+" ("+doc.get("filedate")+")\n");
				retVal.append("\t"+doc.get("filename")+"\n\n");
			}
		}catch(IOException ioe){
			retVal.append(ioe.getMessage() + "\n");
		}
		return retVal.toString();
	}
	
	public String printHTMLSearchResults(Query query, File indexDir){
		StringBuilder retVal = new StringBuilder();
		retVal.append("<html><body>");
		try{
			Directory fsDir=new SimpleFSDirectory(FileSystems.getDefault().getPath(indexDir.getAbsolutePath()));
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			IndexSearcher is = new IndexSearcher(ireader);
			is.search(query, collector);
			
			ScoreDoc[] hits=collector.topDocs().scoreDocs;
			System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId=hits[i].doc;
				Document doc = is.doc(docId);
				retVal.append("<p><h3>"+doc.get("title")+" ("+doc.get("filedate")+")</h3><br/>");
				retVal.append(doc.get("filename")+"<br/><br/></p>");
			}
		}catch(IOException ioe){
			retVal.append(ioe.getMessage() + "<br/>");
		}
		retVal.append("</body></html>");
		return retVal.toString();
	}
	
	private static String getDocumentText(String location){
		File file = new File(location);
		DocumentHandler handler = getHandler(location);
		return handler.getText(file);
	}
	
	protected static DocumentHandler getHandler(String fileName){
		if(fileName.endsWith(".pdf")){
			return new PDFHandler();
		}else{
			return null;
		}
	}

	/*
	 * public static List<ResultData> getResults1(BoolQueryBuilder builder,
	 * List<RequiredHighlight> rh) {
	 * 
	 * if (builder == null) { return null; }
	 * 
	 * List<ResultData> results = new ArrayList<ResultData>();
	 * 
	 * for (Contact contact : contactRepository.search(builder)) {
	 * System.out.println(contactRepository.search(builder).toString());
	 * results.add(new ResultData(contact.getFirstname(), contact.getLastname(),
	 * contact.getNote(), "")); }
	 * 
	 * return results; }
	 
}*/
