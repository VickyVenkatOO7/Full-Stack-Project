package com.tri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tri.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
