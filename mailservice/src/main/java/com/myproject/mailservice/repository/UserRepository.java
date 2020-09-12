package com.myproject.mailservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myproject.mailservice.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
	
	User getByUsername(String username);
	User getByUsernameAndPassword(String username,String password);

	   

}
