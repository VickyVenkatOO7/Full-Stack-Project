package com.tri.request;

import java.util.List;

import com.tri.model.Address;
import com.tri.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
	
	private Long id;
	private String name;
	private String description;
	private String cuisineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String> images;
}
