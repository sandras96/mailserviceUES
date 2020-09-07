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
	List<Message> findByTags_IdAndAccountId(Long tagId, Long accountId);

	@Query(value="SELECT m.* FROM message m LEFT JOIN accounts a ON m.account_id = a.account_id WHERE a.user_id =?", nativeQuery = true)
	List<Message> findMessagesByEuserId(Long id);
	
	@Query(value="select m.* from message m inner join message_tag mt on m.message_id = mt.message_id left join tag t on mt.tag_id = t.tag_id where t.tag_id = ? and t.user_id=? group by m.message_id;", nativeQuery = true)
	List<Message> findByTags_IdAndEuserId(Long tagId, Long userId);
	
	@Query(value="SELECT * FROM message m WHERE m.account_id=? ORDER BY m.subject_mess ASC", nativeQuery = true)
	List<Message> findAllSortBySubject(Long id);
	
	@Query(value="SELECT * FROM message m WHERE m.account_id=? ORDER BY m.from_message ASC", nativeQuery = true)
	List<Message> findAllSortBySender(Long id);
	
	@Query(value="SELECT * FROM message m WHERE m.account_id=? ORDER BY m.date_time DESC", nativeQuery = true)
	List<Message> findAllSortByDatetime(Long id);
	
	@Query(value="SELECT * FROM message WHERE content LIKE ? and account_id = ?", nativeQuery = true)
	List<Message> findByContent(String text, Long id);
	
}
