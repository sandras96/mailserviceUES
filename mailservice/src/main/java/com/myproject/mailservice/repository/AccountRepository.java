package com.myproject.mailservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mailservice.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account getByDisplayName(String displayName);
	
	//@Query("SELECT u FROM accounts u WHERE u.euser.id = :id")
	List<Account> findAccountsByEuserId(Long id);
	Account findByUsername(String username);
	//List<Account> findByDisplayName(String displayName);
}
