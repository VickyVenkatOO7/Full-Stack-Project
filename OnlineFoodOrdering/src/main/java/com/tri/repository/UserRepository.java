package com.tri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tri.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String username);
	
	
}