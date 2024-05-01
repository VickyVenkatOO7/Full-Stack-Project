package com.tri.service;

import java.util.List;

import com.tri.dto.RestaurantDto;
import com.tri.model.Restaurant;
import com.tri.model.User;
import com.tri.request.CreateRestaurantRequest;

public interface RestaurantService {
	
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatRestaurantRequest) throws Exception;
	
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	public List<Restaurant> getAllRestaurant();
	
	public List<Restaurant> searchRestaurants(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception;
	
	public Restaurant getRestaurantByUserId(Long id) throws Exception;
	
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;
	
	public Restaurant updateRestaurantStatus(Long id) throws Exception;

}