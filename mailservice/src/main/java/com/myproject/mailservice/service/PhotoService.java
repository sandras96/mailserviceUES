package com.myproject.mailservice.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mailservice.dto.PhotoDTO;
import com.myproject.mailservice.entity.Photo;
import com.myproject.mailservice.repository.PhotoRepository;

@Service
public class PhotoService implements PhotoInterface {
	
	
	@Autowired
	PhotoRepository photoRepository;
	
	@Override
	public List<Photo> getAll() {
		// TODO Auto-generated method stub
		return photoRepository.findAll();
	}

	@Override
	public Photo getOne(Long idPhoto) {
		// TODO Auto-generated method stub
		return photoRepository.getOne(idPhoto);
	}

	@Override
	public Photo save(Photo photo) {
		// TODO Auto-generated method stub
		return photoRepository.save(photo);
	}
	

	@Override
	public void delete(Long id) {
		Photo toDelete = getOne(id);
		if(toDelete !=null) {
			photoRepository.deleteById(id);	
		}
		
		
	}

	
	@Override
	public List<Photo> findPhotoByUserId(Long id) {
		return photoRepository.findPhotoByContactId(id);
	}

	@Override
	public Photo edit(PhotoDTO photo, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

	


}
