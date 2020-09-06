package com.myproject.mailservice.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.ContactDTO;
import com.myproject.mailservice.dto.MessageDTO;
import com.myproject.mailservice.elasticsearch.filters.CyirilLatinConverter;
import com.myproject.mailservice.elasticsearch.model.AdvancedQuery;
import com.myproject.mailservice.elasticsearch.model.RequiredHighlight;
import com.myproject.mailservice.elasticsearch.model.ResultData;
import com.myproject.mailservice.elasticsearch.model.SearchType;
import com.myproject.mailservice.elasticsearch.repository.ContactRepositoryElasticSearch;
import com.myproject.mailservice.elasticsearch.repository.MessageRepositoryElasticSearch;
import com.myproject.mailservice.elasticsearch.search.GeneralSearchService;
import com.myproject.mailservice.elasticsearch.search.QueryBuilder;
import com.myproject.mailservice.elasticsearch.search.ResultRetriever;
import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Message;
import com.myproject.mailservice.entity.User;
import com.myproject.mailservice.repository.AccountRepository;
import com.myproject.mailservice.repository.ContactRepository;
import com.myproject.mailservice.repository.MessageRepository;
import com.myproject.mailservice.repository.UserRepository;

@RestController
@RequestMapping(value = "/elasticsearch")

public class SearchController {

	@Autowired(required=true)
	private ContactRepositoryElasticSearch contactRepo;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired(required=true)
	private MessageRepositoryElasticSearch messageRepo;
	
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private GeneralSearchService generalSearchService;

	@Autowired
	private UserRepository userService;

	@Autowired
	private com.myproject.mailservice.elasticsearch.search.ResultRetriever resultRetriever;
	
	
	
	@GetMapping(value = "/search/contactFuzzy/{fuzzy}")
	public Iterable<ContactDTO> searchFuzzyContact(@PathVariable("fuzzy") String fuzzyQuery) throws Exception {
		System.out.println("CONTACTS serach fuzzy ---------------" + fuzzyQuery);
		for (Contact c : contactRepository.findAll()) {
			contactRepo.save(c);
		}

		List<ContactDTO> cDTO = new ArrayList<ContactDTO>();
		List<Contact> cl = contactRepo.findFuzzy(CyirilLatinConverter.cir2lat(fuzzyQuery));

		for (Contact co : cl) {
			ContactDTO cD = new ContactDTO(co);
			cDTO.add(cD);
		}
		return cDTO;
	}

	@GetMapping(value = "/search/contactPhrase/{phrase}") 
	public Iterable<ContactDTO> searchByPhrase(@PathVariable("phrase") String phraseQuery) throws Exception {
		System.out.println("CONTACTS serach by phrase --------------- " + phraseQuery);
		for (Contact c : contactRepository.findAll()) {
			contactRepo.save(c);
		}

		List<ContactDTO> cDTO = new ArrayList<ContactDTO>();
		List<Contact> cl = contactRepo.findPhrase(CyirilLatinConverter.cir2lat(phraseQuery));

		for (Contact co : cl) {
			ContactDTO cD = new ContactDTO(co);
			cDTO.add(cD);
		}
		return cDTO;
	}

	
	/*
	 * @GetMapping(value = "/search/searchByFirstName/{id}/{firstName}") public
	 * Iterable<ContactDTO> searchByFirstName1(@PathVariable("id") Long
	 * id, @PathVariable("firstName") String firstName) throws Exception {
	 * 
	 * System.out.println(firstName + id);
	 * System.out.println("CONTACTS serach by first name ---------------");
	 * List<Contact> contacts = contactRepository.findContactsByEuserId(id);
	 * System.out.println("Kontakti lista jeee" + contacts); for(Contact c :
	 * contactRepository.findContactsByEuserId(id)) { contactRepo.save(c);
	 * System.out.println("sacuvao je contact repo " + c); } List<ContactDTO> cDTO =
	 * new ArrayList<ContactDTO>();
	 * System.out.println("sacuvao je contact repo broj" + contactRepo.count());
	 * List<Contact> cl =
	 * contactRepo.findByfirstname(CyirilLatinConverter.cir2lat(firstName));
	 * System.out.println("druga jebena lista je " + cl);
	 * if(contactRepo.existsById(Integer.parseInt(Long.valueOf(id).toString()))) {
	 * System.out.println("asasdjsjfjds"); }
	 * 
	 * for(Contact co: cl) { ContactDTO cD = new ContactDTO(co);
	 * if(cD.getUser().getId() == id) { cDTO.add(cD); }
	 * 
	 * } return cDTO; }
	 */
		  

	@GetMapping(value="/search/searchByFirstName/{firstName}")
	public Iterable<ContactDTO> searchByFirstName(@PathVariable("firstName") String firstName) throws Exception {
		System.out.println("CONTACTS serach by first name ---------------" + firstName);
		for(Contact c : contactRepository.findAll()) {
			contactRepo.save(c);
		}
		List<ContactDTO> cDTO = new ArrayList<ContactDTO>();	
		List<Contact> cl = contactRepo.findByfirstname(CyirilLatinConverter.cir2lat(firstName));
		
		for(Contact co: cl) {
			ContactDTO cD = new ContactDTO(co);		
			cDTO.add(cD);
		}		
		return cDTO;
	}
	@GetMapping(value="/search/searchByLastName/{lastName}")
	public Iterable<ContactDTO> searchByLastName(@PathVariable("lastName") String lastName) throws Exception {
		System.out.println("CONTACTS serach by Last name ---------------" + lastName);
		for(Contact c : contactRepository.findAll()) {
			contactRepo.save(c);
		}
		List<ContactDTO> cDTO = new ArrayList<ContactDTO>();	
		List<Contact> cl = contactRepo.findBylastname(CyirilLatinConverter.cir2lat(lastName));
		
		for(Contact co: cl) {
			ContactDTO cD = new ContactDTO(co);		
			cDTO.add(cD);
		}		
		return cDTO;
	}
	@GetMapping(value="/search/searchByNote/{note}")
	public Iterable<ContactDTO> searchByNote(@PathVariable("note") String note) throws Exception {
		System.out.println("CONTACTS serach by NOTE ---------------" + note);
		note = "%"+note+"%";
		List<Contact> contacts = contactRepository.findBynote(CyirilLatinConverter.cir2lat(note));
		List<ContactDTO> cDTO = new ArrayList<ContactDTO>();	
		
		for(Contact co: contacts) {
			ContactDTO cD = new ContactDTO(co);		
			cDTO.add(cD);
		}		
		return cDTO;
	}

	@PostMapping(value = "/search/searchByLastName")
	public Iterable<ContactDTO> searchByLastName(@RequestParam("userId") Long userId,
			@RequestParam("lastName") String simpleQuery) throws Exception {
		System.out.println("CONTACTS serach by last name ---------------");
		for (Contact c : contactRepository.findAll()) {
			contactRepo.save(c);
		}
		List<ContactDTO> cDTO = new ArrayList<ContactDTO>();
		List<Contact> cl = contactRepo.findBylastname(CyirilLatinConverter.cir2lat(simpleQuery));

		for (Contact co : cl) {
			ContactDTO cD = new ContactDTO(co);
			cDTO.add(cD);
		}
		return cDTO;
	}

	
	  /// ne zovi ne pisi
	  
	/*
	 * @GetMapping(value="/search-test") public void mockFind() { List<Contact>
	 * contacts = generalSearchService.search("mirk", 1L);
	 * 
	 * List<ContactDTO> dtos = new ArrayList<ContactDTO>();
	 * 
	 * for(Contact contact: contacts) { ContactDTO contactDTO = new
	 * ContactDTO(contact); dtos.add(contactDTO); }
	 * 
	 * return dtos;
	 * 
	 * List<Message> messages = messageRepo.findAll();
	 * 
	 * List<MessageDTO> messDTO = messages .stream() .map(message -> new
	 * MessageDTO(message)) .collect(Collectors.toList());
	 * 
	 * 
	 * mdrs.saveAll(messDTO);
	 * 
	 * 
	 * }
	 */

	@GetMapping(value = "/search-contacts")
	public List<Contact> searchContacts(@RequestParam(required = false) String query) {
		if (query == null || query.equals("")) {
			return contactRepository.findAll();
		}

		return generalSearchService.searchContacts(query);
	}
	/*
	 * @GetMapping(value = "/search-messages") public List<Message>
	 * searchMessages(@RequestParam(required = false) String query) {
	 * System.out.println("aaaaaaaaaaaaaa"); if (query == null || query.equals(""))
	 * { return messageRepository.findAll(); }
	 * 
	 * return generalSearchService.searchMessages(query); }
	 * 
	 * @GetMapping(value = "/search-contacts-test") public List<Contact>
	 * searchContacts1() {
	 * 
	 * 
	 * return generalSearchService.searchContacts("lidija"); }
	 */

/*	@PostMapping(value = "/search/contactBool")
	public ResponseEntity<List<ResultData>> searchByBoolAnd(@RequestParam("firstField") String firstField,
			@RequestParam("firstValue") String firstValue, @RequestParam("secondField") String secondField,
			@RequestParam("secondValue") String secondValue, @RequestParam("thirdField") String thirdField,
			@RequestParam("thirdValue") String thirdValue, @RequestParam("operation") String operation)
			throws Exception {
		for (Contact c : contactRepository.findAll()) {
			contactRepo.save(c);
		}
		org.elasticsearch.index.query.QueryBuilder query1 = QueryBuilder.buildQuery(SearchType.regular, firstField,
				firstValue);
		org.elasticsearch.index.query.QueryBuilder query2 = QueryBuilder.buildQuery(SearchType.regular, secondField,
				secondValue);
		org.elasticsearch.index.query.QueryBuilder query3 = QueryBuilder.buildQuery(SearchType.regular, thirdField,
				thirdValue);

		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		if (operation.equalsIgnoreCase("AND")) {
			builder.must(query1);
			builder.must(query2);
			builder.must(query3);
		} else if (operation.equalsIgnoreCase("OR")) {
			builder.should(query1);
			builder.should(query2);
			builder.should(query3);
		}

		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		rh.add(new RequiredHighlight(firstField, firstValue));
		rh.add(new RequiredHighlight(secondField, secondValue));
		rh.add(new RequiredHighlight(thirdField, thirdValue));
		List<ResultData> results = resultRetriever.getResults(builder, rh);
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
*/
/*	@PostMapping(value = "/search/contactBool", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchByBoolAnd(@RequestBody AdvancedQuery advancedQuery)throws Exception {
		Query query1 =  (Query) QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField1(), advancedQuery.getValue1());
		Query query2 =  (Query) QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField2(), advancedQuery.getValue2());
	
		for (Contact c : contactRepository.findAll()) {
			contactRepo.save(c);
		}
	
	//	org.elasticsearch.index.query.QueryBuilder query1 =  QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField1(), advancedQuery.getValue1());
	//	org.elasticsearch.index.query.QueryBuilder query2 =  QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField2(), advancedQuery.getValue2());

		System.out.println("kveri 1 je " + query1);
		System.out.println("kveri 2 je " + query2);
		BooleanQuery.Builder builder=new BooleanQuery.Builder();
		if (advancedQuery.getOperation().equalsIgnoreCase("AND")) {
			builder.add(query1,BooleanClause.Occur.MUST);
			builder.add(query2,BooleanClause.Occur.MUST);
		} else if (advancedQuery.getOperation().equalsIgnoreCase("OR")) {
			builder.add(query1,BooleanClause.Occur.SHOULD);
			builder.add(query2,BooleanClause.Occur.SHOULD);
		}
//		Query query = builder.build();
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		System.out.println("rhh1" + rh.add(new RequiredHighlight(advancedQuery.getField1(),advancedQuery.getValue1())));
		System.out.println("rhh2" + rh.add(new RequiredHighlight(advancedQuery.getField2(),advancedQuery.getValue2())));
		rh.add(new RequiredHighlight(advancedQuery.getField1(),advancedQuery.getValue1()));
		rh.add(new RequiredHighlight(advancedQuery.getField2(),advancedQuery.getValue2()));
		List<ResultData> results = ResultRetriever.getResults(builder, rh);
		System.out.println("rezultati suu" + results);
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	*/
	
	@PostMapping(value = "/search/contactBool", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchByBoolAnd(@RequestBody AdvancedQuery advancedQuery)throws Exception {
		for (Contact c : contactRepository.findAll()) {
			contactRepo.save(c);
		}
		org.elasticsearch.index.query.QueryBuilder query1 = QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField1(), advancedQuery.getValue1());
		org.elasticsearch.index.query.QueryBuilder query2 = QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField2(), advancedQuery.getValue2());

		System.out.println("kveri 1 je " + query1);
		System.out.println("kveri 2 je " + query2);
		
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		if (advancedQuery.getOperation().equalsIgnoreCase("AND")) {
			builder.must(query1);
			builder.must(query2);
		} else if (advancedQuery.getOperation().equalsIgnoreCase("OR")) {
			builder.should(query1);
			builder.should(query2);
		}

		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		System.out.println("rh1: " + rh.add(new RequiredHighlight(advancedQuery.getField1(),advancedQuery.getValue1())));
		System.out.println("rh2: " + rh.add(new RequiredHighlight(advancedQuery.getField2(),advancedQuery.getValue2())));
		
		rh.add(new RequiredHighlight(advancedQuery.getField1(),advancedQuery.getValue1()));
		rh.add(new RequiredHighlight(advancedQuery.getField2(),advancedQuery.getValue2()));
		List<ResultData> results = resultRetriever.getResults(builder, rh);
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "/test")
	public String test() throws Exception {
		String file = "data";
		File filess = new File(file);
		System.out.println(filess.getAbsolutePath());
		return filess.getAbsolutePath();
	}
//-------------------------PORUKE---------------------------------------------

	@GetMapping(value = "/search-messages/{id}")
	public List<Message> searchMessages(@RequestParam(required = false) String query, @PathVariable("id") Long id) {
		if (query == null || query.equals("")) {
			return messageRepository.findMessagesByAccountId(id);
				
		}
//		return messageRepo.findAll();
		return generalSearchService.searchMessages(query);
		
		}
	
	
	 @GetMapping(value = "/search/searchBySubject/{id}/{subject}") 
	  public Iterable<MessageDTO> searchBySubject(@PathVariable("id") Long id, @PathVariable("subject") String subject) throws Exception {
		  System.out.println("MAILS serach by SUBJECT ---------------" + subject + "id: " + id);
	  
		  List<Message> messages = messageRepository.findMessagesByAccountId(id);
		  System.out.println("Messages lista jeee" + messages); 
		  for(Message m : messageRepository.findMessagesByAccountId(id)) {
			
			  messageRepo.save(m);
		  	} 
		  
		  List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		  List<Message> messages1 = messageRepo.findBySubject(CyirilLatinConverter.cir2lat(subject));
	  
		  for(Message m: messages1) { 
			  	m.setAccount(accountRepository.getOne(id));
			  	MessageDTO messageDTO = new MessageDTO(m);
			  	messagesDTO.add(messageDTO);
		  			} 
		  		return messagesDTO;
	 }
		
	 @GetMapping(value="/search/searchBySender/{id}/{from}")
	   public Iterable<MessageDTO> searchByFromAndTo(@PathVariable("id") Long id, @PathVariable("from") String from) throws Exception {
		  System.out.println("MAILS serach by SENDER ---------------" + from + "id: " + id);
		  
		  List<Message> messages = messageRepository.findMessagesByAccountId(id);
		  System.out.println("Messages lista jeee" + messages); 
		  for(Message m : messageRepository.findMessagesByAccountId(id)) {
			
			  messageRepo.save(m);
		  	} 
		  List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		  List<Message> messages1 = messageRepo.findByFrom(CyirilLatinConverter.cir2lat(from));
	  
		  for(Message m: messages1) { 
			  	m.setAccount(accountRepository.getOne(id));
			  	MessageDTO messageDTO = new MessageDTO(m);
			  	messagesDTO.add(messageDTO);
		  			} 
		  		return messagesDTO;
	   }
	 
	 @GetMapping(value="/search/searchByContent/{id}/{content}")
		public Iterable<MessageDTO> searchByContent(@PathVariable("id") Long id, @PathVariable("content") String content) throws Exception {
			System.out.println("MAILS serach by CONTENT ---------------" + content);
			content = "%"+content+"%";
			  List<Message> messages = messageRepository.findMessagesByAccountId(id);
			  System.out.println("Messages lista jeee" + messages); 
			  for(Message m : messageRepository.findMessagesByAccountId(id)) {
				
				  messageRepo.save(m);
			  	} 
			
			 List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
			 List<Message> messages1 = messageRepository.findByContent(CyirilLatinConverter.cir2lat(content), id);
			 for(Message m: messages1) { 
				  	m.setAccount(accountRepository.getOne(id));
				  	MessageDTO messageDTO = new MessageDTO(m);
				  	messagesDTO.add(messageDTO);
			  			} 
			  		return messagesDTO;
		}
	 
		}
