package com.tri.request;

import com.tri.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
	
	private Long restaurantId;
	private Address deliveryAddress;
}
