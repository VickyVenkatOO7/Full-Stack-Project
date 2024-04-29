package com.tri.service;

import java.util.List;

import com.tri.model.Category;

public interface CategoryService {
	
	public Category createCategory(String name, Long userId) throws Exception;
	
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception;
	
	public Category findCategoryByid(Long id) throws Exception;
}
