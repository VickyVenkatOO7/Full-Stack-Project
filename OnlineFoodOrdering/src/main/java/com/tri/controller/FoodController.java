package com.tri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tri.model.Food;
import com.tri.model.Restaurant;
import com.tri.model.User;
import com.tri.request.CreateFoodRequest;
import com.tri.service.FoodService;
import com.tri.service.RestaurantService;
import com.tri.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
											@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		
		List<Food> foods = foodService.searchFood(name);
		
		return new ResponseEntity<>(foods, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestaurantFood(
			@RequestParam boolean vegetarian,
			@RequestParam boolean seasonal,
			@RequestParam(required = false) String food_category,
			@PathVariable Long restaurantId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		
		List<Food> foods = foodService.getRestaurantsFood(restaurantId, vegetarian, seasonal, food_category);
		
		return new ResponseEntity<>(foods, HttpStatus.OK);
	}
}
