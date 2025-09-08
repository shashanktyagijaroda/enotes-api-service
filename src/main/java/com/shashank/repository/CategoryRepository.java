package com.shashank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashank.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
