package com.myproject.mailservice.elasticsearch.filters;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class CyrilToLatinFilter extends TokenFilter {
	
	private CharTermAttribute termAttribute;

	public CyrilToLatinFilter(TokenStream input) {
		super(input);
		termAttribute=(CharTermAttribute)input.addAttribute(CharTermAttribute.class);
	}

	public boolean incrementToken()throws IOException {
	       if (input.incrementToken()) {
	        String text=termAttribute.toString();
	        termAttribute.setEmpty();
	        termAttribute.append(CyirilLatinConverter.cir2lat(text));
	        return true;
	       }
	       return false;
	   }

}
