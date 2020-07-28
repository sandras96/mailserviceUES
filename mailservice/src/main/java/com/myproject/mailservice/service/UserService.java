package com.myproject.mailservice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.dto.UserDTO;
import com.myproject.mailservice.entity.User;
import com.myproject.mailservice.repository.UserRepository;


@Service
public class UserService implements UserInterface {
	
	@Autowired
	UserRepository userRepository;
	
	/*
	 * @Override public Page<User> getAllPaged(Pageable pageRequest) { return
	 * userRepository.findAll(pageRequest); }
	 */

	@Override
	public User getByUsername(String username) {
		return userRepository.getByUsername(username);
	}

	@Override
	public User getByUsernameAndUserPassword(String username, String password) {
		return userRepository.getByUsernameAndPassword(username, password);
	}

	@Override
	public User getOne(Long userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		User deletedUser = getOne(id);
		if(deletedUser!=null){
			userRepository.deleteById(id);
		}
	}

	@Override
	public User edit(UserDTO user, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	

	/*
	 * @Override public User edit(UserDTO user, Long id) { user.setId(id);
	 * //user=toDTO.convert(getOne(id));
	 * System.out.println("userdto "+user.getId()+" "+user.getFirstName()+" "+user.
	 * getLastName()+" "+user.getUserPassword()); User editedUser =
	 * toUser.convert(user); System.out.println(editedUser.toString());
	 * System.out.println("edited user"+editedUser.getFirstName()+" "+editedUser.
	 * getLastName()); save(editedUser); return editedUser; }
	 */

}
