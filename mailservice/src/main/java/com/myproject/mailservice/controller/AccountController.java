package com.myproject.mailservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.AccountDTO;
import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.service.AccountInterface;
import com.myproject.mailservice.service.UserInterface;

@RestController
@RequestMapping(value="mailservice/accounts")
public class AccountController {
	
	@Autowired
	private AccountInterface accountService;
	
	@Autowired
	private UserInterface userService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping(value="/{id}")
		public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id")Long id){
			logger.info("GET request for account with id: " + id);
			Account account = accountService.getOne(id);
			if(account == null) {
				logger.error("Account with id: "+ id +" not found.");
				return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
			}
	}
	
	
	 @GetMapping(value = "/user/{id}")
	    public ResponseEntity<List<AccountDTO>> getAccountsByUser(@PathVariable("id") Long id){
		 	logger.info("GET request for all accounts from user with id: " + id);
		 	List<Account> accounts=accountService.findAccountsByEuserId(id);
	        List<AccountDTO>accountsDTO=new ArrayList<>();
	            for (Account a:accounts) {
	            	 accountsDTO.add(new AccountDTO(a));
	            	}
	         
	        return new ResponseEntity<List<AccountDTO>>(accountsDTO,HttpStatus.OK);
	    }
	 
	 
	 
	 @PostMapping(value="/addAccount")
		public ResponseEntity<AccountDTO> addAccount(@RequestParam("smtpAddress") String smtpAddress,
													 @RequestParam("smtpPort") int smtpPort,
													 @RequestParam("inServerType") int inServerType,
													 @RequestParam("inServerAddress") String inServerAddress,
													 @RequestParam("inServerPort") int inServerPort,
													 @RequestParam("username") String username,
													 @RequestParam("password") String password,
													 @RequestParam("displayName") String displayName,
													 @RequestParam("euser") Long id){
		 
		 	logger.info("Post request for adding new account with username: " + username +" for user with id: " + id);
		 	Account account1 = accountService.findByUsername(username);
		 	if(account1 != null) {
		 		return new ResponseEntity<AccountDTO>(HttpStatus.FORBIDDEN);
		 		}
			Account account = new Account();
			account.setSmtpAddress(smtpAddress);
			account.setSmtpPort(smtpPort);
			account.setInServerType(inServerType);
			account.setInServerAddress(inServerAddress);
			account.setInServerPort(inServerPort);
			account.setUsername(username);
			account.setPassword(password);
			account.setDisplayName(displayName);
			account.setUser(userService.getOne(id));
			
			account = accountService.save(account);
			return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.CREATED);
		}
	 
	 @PutMapping(value="/editAccount/{id}", consumes="application/json")
		public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO,@PathVariable("id")Long id){
		 	logger.info("Put request for updating account with id: " + id);
		 	Account account = accountService.getOne(id);
			if(account == null) {
				return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
				}
			
			if(accountDTO.getSmtpAddress().equals("")) {
				account.setSmtpAddress(account.getSmtpAddress());
			}else {
				account.setSmtpAddress(accountDTO.getSmtpAddress());
				}
			
			if(accountDTO.getSmtpPort() == 0) {
				account.setSmtpPort(account.getSmtpPort());
			}else {
				account.setSmtpPort(accountDTO.getSmtpPort());
				}
			
			if(accountDTO.getInServerType() == 0) {
				account.setInServerType(account.getInServerType());
			}else {
				account.setInServerType(accountDTO.getInServerType());
				}
			
			if(accountDTO.getInServerAddress().equals("")) {
				account.setInServerAddress(account.getInServerAddress());
			}else {
				account.setInServerAddress(accountDTO.getInServerAddress());
				}
			
			if(accountDTO.getInServerPort() == 0) {
				account.setInServerPort(account.getInServerPort());
			}else {
				account.setInServerPort(accountDTO.getInServerPort());
				}
			
			if(accountDTO.getUsername().equals("")) {
				account.setUsername(account.getUsername());
			}else {
				account.setUsername(accountDTO.getUsername());
				}
			
			if(accountDTO.getPassword().equals("")) {
				account.setPassword(account.getPassword());
			}else {
				account.setPassword(accountDTO.getPassword());
				}
			
			if(accountDTO.getDisplayName().equals("")) {
				account.setDisplayName(account.getDisplayName());
			}else {
				account.setDisplayName(accountDTO.getDisplayName());
				}

				
			account = accountService.save(account);
			return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
			
		}
	 
	 @DeleteMapping(value="/deleteAccount/{id}")
		public ResponseEntity<Void> deleteAccount(@PathVariable("id")Long id){
		 	logger.info("Delete request, delete account with id: " + id);
			Account account = accountService.getOne(id);
			if(account == null) {
				logger.error("Account with id: "+ id +" not found.");
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}else {
				accountService.delete(id);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
	 
}
