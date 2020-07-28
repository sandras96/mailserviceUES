package com.myproject.mailservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mailservice.entity.Attachment;


public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

	List<Attachment> getByMessageId(Long id);
}
