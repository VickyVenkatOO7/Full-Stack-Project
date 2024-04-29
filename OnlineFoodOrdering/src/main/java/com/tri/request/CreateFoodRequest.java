package com.tri.request;

import java.util.List;

import com.tri.model.Category;
import com.tri.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
	
	private String name;
	private String description;
	private Long price;
	
	private Category category;
	private List<String> images;
	
	private Long restaurantId;
	private boolean vegetarian;
	private boolean seasional;
	private List<IngredientsItem> ingredients;
}
