package com.shashank.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shashank.dto.CategoryDto;
import com.shashank.dto.CategoryResponseDto;
import com.shashank.entity.Category;
import com.shashank.exception.ResourceNotFoundException;
import com.shashank.repository.CategoryRepository;
import com.shashank.service.CategoryService;
import com.shashank.util.Validation;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private Validation validation;

	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		// validation checking
		validation.categoryValidation(categoryDto);
		Category category = mapper.map(categoryDto, Category.class);

		if (ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
		} else {
			updateCategory(category);
		}

		Category saveCategory = categoryRepository.save(category);
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}

		return true;
	}

	private void updateCategory(Category category) {
		Optional<Category> findById = categoryRepository.findById(category.getId());
		if (findById.isPresent()) {
			Category existCategory = findById.get();
			category.setCreatedBy(existCategory.getCreatedBy());
			category.setCreatedOn(existCategory.getCreatedOn());
			category.setIsDeleted(existCategory.getIsDeleted());

			category.setUpdatedBy(1);
			category.setUpdatedOn(new Date());
		}
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepository.findByIsDeletedFalse();
		List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
		return categoryDtoList;
	}

	@Override
	public List<CategoryResponseDto> getActiveCategory() {
		List<Category> categories = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
		List<CategoryResponseDto> categoryList = categories.stream()
				.map(cat -> mapper.map(cat, CategoryResponseDto.class)).toList();
		return categoryList;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) throws Exception {
		Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with id=" + id));
		if (!ObjectUtils.isEmpty(category)) {
			return mapper.map(category, CategoryDto.class);
		}
		return null;

	}

	@Override
	public Boolean deleteCategory(Integer id) {
		Optional<Category> findByCategory = categoryRepository.findById(id);
		if (findByCategory.isPresent()) {
			Category category = findByCategory.get();
			category.setIsDeleted(true);
			categoryRepository.save(category);
			return true;
		}
		return false;
	}

}
