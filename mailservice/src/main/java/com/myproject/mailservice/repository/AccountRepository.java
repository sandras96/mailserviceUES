package com.myproject.mailservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myproject.mailservice.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account getByDisplayName(String displayName);
	
	List<Account> findAccountsByEuserId(Long id);
	Account findByUsername(String username);
	//List<Account> findByDisplayName(String displayName);
	
	
	@Query(value="select distinct a.account_id from accounts a join message m on m.account_id = a.account_id where m.message_id = ?", nativeQuery = true)
	Long findByMessageId(Long id);
	
}
