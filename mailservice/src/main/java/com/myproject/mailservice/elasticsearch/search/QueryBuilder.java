package com.myproject.mailservice.elasticsearch.search;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;

import com.myproject.mailservice.elasticsearch.model.SearchType;


public class QueryBuilder implements org.elasticsearch.index.query.QueryBuilder {
	
	private static int maxEdits = 1;

	public static int getMaxEdits(){
		return maxEdits;
	}

	public static void setMaxEdits(int maxEdits){
		QueryBuilder.maxEdits = maxEdits;
	}

	public static org.elasticsearch.index.query.QueryBuilder buildQuery(SearchType queryType, String field, String value) throws IllegalArgumentException, ParseException{
		String errorMessage = "";
		if(field == null || field.equals("")){
			errorMessage += "Field not specified";
		}
		if(value == null){
		if(!errorMessage.equals("")) errorMessage += "\n";
			errorMessage += "Value not specified";
		}
		if(!errorMessage.equals("")){
			throw new IllegalArgumentException(errorMessage);
		}
	
			org.elasticsearch.index.query.QueryBuilder retVal = null;
		if(queryType.equals(SearchType.regular)){
			retVal = QueryBuilders.termQuery(field, value);
		}else if(queryType.equals(SearchType.fuzzy)){
			retVal = QueryBuilders.fuzzyQuery(field, value).fuzziness(Fuzziness.fromEdits(maxEdits));
		}else if(queryType.equals(SearchType.prefix)){
			retVal = QueryBuilders.prefixQuery(field, value);
		}else if(queryType.equals(SearchType.range)){
		String[] values = value.split(" ");
			retVal = QueryBuilders.rangeQuery(field).from(values[0]).to(values[10]);
		}else{
			retVal = QueryBuilders.matchPhraseQuery(field, value);
		}
	
		return retVal;
	}

	@Override
	public String getWriteableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeTo(StreamOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query toQuery(QueryShardContext context) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query toFilter(QueryShardContext context) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.elasticsearch.index.query.QueryBuilder queryName(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float boost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public org.elasticsearch.index.query.QueryBuilder boost(float boost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
