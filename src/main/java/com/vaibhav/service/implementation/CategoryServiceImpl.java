package com.vaibhav.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.entity.Category;
import com.vaibhav.repository.CategoryRepository;
import com.vaibhav.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category createCategory(Category category) {
		
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(Long id) throws Exception {
		Optional<Category> existingCategoryOptional = categoryRepository.findById(id);
		if(!existingCategoryOptional.isPresent()) {
			throw new Exception("category not found");
		}
		return existingCategoryOptional.get();
	}

	@Override
	public void deleteCategory(Long id) throws Exception {
		System.out.println("deleting category iwth id: " + id);
		this.getCategory(id);
		categoryRepository.deleteById(id);
	}

}
