package com.vaibhav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
