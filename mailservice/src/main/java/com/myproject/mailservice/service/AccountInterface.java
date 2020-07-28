package com.myproject.mailservice.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myproject.mailservice.dto.AccountDTO;
import com.myproject.mailservice.entity.Account;



public interface AccountInterface {
	
	List<Account> getAll();
	Page<Account> getAllPaged(Pageable pageRequest);
	Account getOne(Long idAcc);
	Account save(Account account);
	void delete(Long id);
	Account edit(AccountDTO account,Long id);
	Account getByDisplayName(String displayName);
	List<Account> findAccountsByEuserId(Long id);
	Account findByUsername(String username);

}
