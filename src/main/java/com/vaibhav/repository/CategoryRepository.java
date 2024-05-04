package com.vaibhav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
