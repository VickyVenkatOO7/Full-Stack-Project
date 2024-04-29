package com.tri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tri.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	public Cart findByCustomerId(Long userId);
	
	
}
