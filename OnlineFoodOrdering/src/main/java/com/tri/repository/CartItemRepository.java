package com.tri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tri.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
}
