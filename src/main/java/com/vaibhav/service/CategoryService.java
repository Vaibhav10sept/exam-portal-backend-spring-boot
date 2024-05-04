package com.vaibhav.service;

import java.util.List;
import java.util.Set;

import com.vaibhav.entity.Category;
import com.vaibhav.entity.Role;

public interface CategoryService {
	Category createCategory(Category category);
	
	Category updateCategory(Category category);
	
	List<Category> getAllCategories();
	
	Category getCategory(Long id) throws Exception;
	
	void deleteCategory(Long id) throws Exception;
}
