package com.myproject.mailservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mailservice.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	Authority getByName(String name);
}
