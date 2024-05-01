package com.tri.service;

import java.util.List;

import com.tri.model.Category;
import com.tri.model.Food;
import com.tri.model.Restaurant;
import com.tri.request.CreateFoodRequest;

public interface FoodService {
	
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId,
										 boolean isVegetarian,
										 boolean isSeasonal,
										 boolean isNonveg, 
										 String foodCategory
	);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
