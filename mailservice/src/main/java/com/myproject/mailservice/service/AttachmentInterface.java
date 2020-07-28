package com.myproject.mailservice.service;

import java.util.List;

import com.myproject.mailservice.dto.AttachmentDTO;
import com.myproject.mailservice.entity.Attachment;

public interface AttachmentInterface {

	List<Attachment> getAllByMessageId(Long id);
	Attachment getOne(Long id);
	Attachment save(Attachment attachment);
	Attachment edit(AttachmentDTO attachment, Long id);
	void delete(Long id);

}
