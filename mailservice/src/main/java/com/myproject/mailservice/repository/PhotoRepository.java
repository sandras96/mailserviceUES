package com.myproject.mailservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mailservice.entity.Photo;


public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
	List<Photo> findPhotoByContactId(Long id);
	
}
