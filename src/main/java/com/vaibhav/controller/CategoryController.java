package com.vaibhav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.entity.Category;
import com.vaibhav.service.CategoryService;

import jakarta.persistence.criteria.CriteriaBuilder.Case;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.createCategory(category));
	}
	
	@PutMapping
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.updateCategory(category));
	}
	
	@GetMapping()
	public ResponseEntity<List<Category>> getAllCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
		Category existingCategory;
		try {
			existingCategory = categoryService.getCategory(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("category with this id not found!");
		}
		return ResponseEntity.ok(existingCategory);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		try {
			categoryService.deleteCategory(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("category not found!");
		}
		return ResponseEntity.ok("category deleted successfully");
	}
	
}
