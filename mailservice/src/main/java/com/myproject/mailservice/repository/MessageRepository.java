package com.myproject.mailservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myproject.mailservice.entity.Contact;
import com.myproject.mailservice.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Long>{

	Message getBySubject(String subject);
	Message findBySubject(String subject);
	List<Message> findMessagesByAccountId(Long id);
	
	@Query(value="SELECT * FROM message m WHERE m.account_id=? ORDER BY m.subject_mess ASC", nativeQuery = true)
	List<Message> findAllSortBySubject(Long id);
	
	@Query(value="SELECT * FROM message m WHERE m.account_id=? ORDER BY m.from_message DESC", nativeQuery = true)
	List<Message> findAllSortBySender(Long id);
	
	@Query(value="SELECT * FROM message m WHERE m.account_id=? ORDER BY m.date_time DESC", nativeQuery = true)
	List<Message> findAllSortByDatetime(Long id);
	
	@Query(value="SELECT * FROM message WHERE content LIKE ? and account_id = ?", nativeQuery = true)
	List<Message> findByContent(String text, Long id);
	
}
