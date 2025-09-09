package com.shashank.service;

import java.util.List;

import com.shashank.dto.CategoryDto;
import com.shashank.dto.CategoryResponseDto;

public interface CategoryService {

	public Boolean saveCategory(CategoryDto categoryDto);
	
	public List<CategoryDto> getAllCategory();

	public List<CategoryResponseDto> getActiveCategory();

	public CategoryDto getCategoryById(Integer id) throws Exception;

	public Boolean deleteCategory(Integer id);
	
	
}
