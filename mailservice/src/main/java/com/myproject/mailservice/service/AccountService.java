package com.myproject.mailservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.dto.AccountDTO;
import com.myproject.mailservice.entity.Account;
import com.myproject.mailservice.repository.AccountRepository;

@Service
public class AccountService implements AccountInterface {
	
	@Autowired
	AccountRepository accountRepository;
	
	/*
	 * @Autowired DTOtoAccount toAcc;
	 */
	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public Page<Account> getAllPaged(Pageable pageRequest) {
		return accountRepository.findAll(pageRequest);
	}

	@Override
	public Account getOne(Long idCat) {
		return accountRepository.getOne(idCat);
	}

	@Override
	public Account save(Account category) {
		return accountRepository.save(category);
	}

	@Override
	public void delete(Long id) {
		Account deletedCat = getOne(id);
		if(deletedCat!=null){
			accountRepository.deleteById(id);
		}
	}

	@Override
	public Account getByDisplayName(String displayName) {
		return accountRepository.getByDisplayName(displayName);
	}

	/*
	 * @Override public Account edit(AccountDTO account, Long id) {
	 * account.setId(id); Account editedAccount = toAcc.convert(account);
	 * accountRepository.save(editedAccount); return editedAccount; }
	 */

	@Override
	public List<Account> findAccountsByEuserId(Long id) {
		return accountRepository.findAccountsByEuserId(id);
	}

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public Account edit(AccountDTO account, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findByMessageId(Long id) {
		// TODO Auto-generated method stub
		return accountRepository.findByMessageId(id);
	}


}
