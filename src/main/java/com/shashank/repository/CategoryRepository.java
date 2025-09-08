package com.shashank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashank.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	List<Category> findByIsActiveTrue();

}
