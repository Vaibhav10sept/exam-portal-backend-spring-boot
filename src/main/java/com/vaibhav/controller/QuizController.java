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
import com.vaibhav.entity.Quiz;
import com.vaibhav.service.CategoryService;
import com.vaibhav.service.QuizService;

import jakarta.persistence.criteria.CriteriaBuilder.Case;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(quizService.createQuiz(quiz));
	}
	
	@PutMapping
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(quizService.updateQuiz(quiz));
	}
	
	@GetMapping()
	public ResponseEntity<List<Quiz>> getAllQuizes() {
		return ResponseEntity.ok(quizService.getAllQuiz());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getQuizById(@PathVariable Long id) {
		Quiz existingQuiz;
		try {
			existingQuiz = quizService.getQuizById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("quiz with this id not found!");
		}
		return ResponseEntity.ok(existingQuiz);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteQuiz(@PathVariable Long id) {
		try {
			quizService.deleteQuizById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("quiz not found!");
		}
		return ResponseEntity.ok("quiz is deleted successfully");
	}
	
}
