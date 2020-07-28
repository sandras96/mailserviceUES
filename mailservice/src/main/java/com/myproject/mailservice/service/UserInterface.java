package com.myproject.mailservice.service;

import java.util.List;

import com.myproject.mailservice.dto.UserDTO;
import com.myproject.mailservice.entity.User;

public interface UserInterface {

	//Page<User> getAllPaged(Pageable pageRequest);
	User getByUsername(String username);
	User getByUsernameAndUserPassword(String username,String password);
	User getOne(Long userId);
	User save(User user);
	User edit(UserDTO user,Long id);
	void delete(Long id);
	List<User> findAll();
}
