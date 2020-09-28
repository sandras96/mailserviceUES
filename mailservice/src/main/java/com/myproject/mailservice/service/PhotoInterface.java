package com.myproject.mailservice.service;

import java.util.List;

import com.myproject.mailservice.dto.PhotoDTO;
import com.myproject.mailservice.entity.Photo;

public interface PhotoInterface {

	List<Photo> getAll();
	//Page<Photo> getAllPaged(Pageable pageRequest);
	Photo getOne(Long idPhoto);
	Photo save(Photo photo);
	void delete(Long id);
	Photo edit(PhotoDTO photo,Long id);
	//Photo getByDisplayName(String displayName);
	List<Photo> findPhotoByContactId(Long id);
}
