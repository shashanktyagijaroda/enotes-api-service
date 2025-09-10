package com.shashank.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.shashank.dto.CategoryDto;
import com.shashank.exception.ValidationException;

@Component
public class Validation {

	Map<String, Object> error = new LinkedHashMap<>();

	public void categoryValidation(CategoryDto categoryDto) {
		if (ObjectUtils.isEmpty(categoryDto)) {
			throw new IllegalArgumentException("category object/JSON should not be null or empty");
		} else {
			// validation name field
			if (ObjectUtils.isEmpty(categoryDto.getName())) {
				error.put("name", "name field is empty or null");
			} else {
				if (categoryDto.getName().length() < 10) {
					error.put("name", "name length is min 10");
				}
				if (categoryDto.getName().length() > 100) {
					error.put("name", "name length is max 100");
				}
			}
			// validation description

			if (ObjectUtils.isEmpty(categoryDto.getDescription())) {
				error.put("description", "description field is empty or null");
			}
			
			//validation on isActive

			if (ObjectUtils.isEmpty(categoryDto.getIsActive())) {
				error.put("isActive", "isActive field is empty or null");
			} else {
				if(categoryDto.getIsActive() != Boolean.TRUE.booleanValue()
						&& categoryDto.getIsActive()!=Boolean.FALSE.booleanValue()) {
					error.put("isActive","invalid value for isActive field");
				}
			}
		}
		if(!error.isEmpty()) {
			throw new ValidationException(error);
		}
	}
}
