package com.myproject.mailservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.dto.AttachmentDTO;
import com.myproject.mailservice.entity.Attachment;
import com.myproject.mailservice.repository.AttachmentRepository;

@Service
public class AttachmentService implements AttachmentInterface {

	@Autowired
	AttachmentRepository attachmentRepository;
	
	@Override
	public List<Attachment> getAllByMessageId(Long id){
		return attachmentRepository.getByMessageId(id);
	}

	@Override
	public Attachment getOne(Long id) {
		return attachmentRepository.getOne(id);
	}

	@Override
	public Attachment save(Attachment attachment) {
		return attachmentRepository.save(attachment);
	}

	/*
	 * @Override public Attachment edit(AttachmentDTO attachment, Long id) {
	 * attachment.setId(id); Attachment editedAttachment =
	 * toAttachment.convert(attachment);
	 * System.out.println(editedAttachment.toString()); save(editedAttachment);
	 * return editedAttachment; }
	 */

	@Override
	public void delete(Long id) {
		Attachment attachmentToDelete = getOne(id);
		if(attachmentToDelete != null) {
			attachmentRepository.deleteById(id);
		}
		
	}

	@Override
	public Attachment edit(AttachmentDTO attachment, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
