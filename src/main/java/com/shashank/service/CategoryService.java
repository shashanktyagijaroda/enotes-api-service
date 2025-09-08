package com.shashank.service;

import java.util.List;

import com.shashank.entity.Category;

public interface CategoryService {

	public Boolean saveCategory(Category category);
	
	public List<Category> getAllCategory();
	
	
}
