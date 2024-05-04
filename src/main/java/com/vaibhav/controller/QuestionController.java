package com.vaibhav.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
import com.vaibhav.entity.Question;
import com.vaibhav.entity.Quiz;
import com.vaibhav.repository.QuestionRepository;
import com.vaibhav.service.CategoryService;
import com.vaibhav.service.QuestionService;
import com.vaibhav.service.QuizService;

import jakarta.persistence.criteria.CriteriaBuilder.Case;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getAllQuestionForAQuiz(@PathVariable Long qid) {
//		Quiz quiz = new Quiz();
//		quiz.setId(qid);
//		List<Question> questionsList = questionService.getQuestionsOfAQuiz(quiz);
//		return ResponseEntity.ok(questionsList);
		
		Quiz quiz;
		try {
			quiz = quizService.getQuizById(qid);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("quiz with this id not found!");
		}
		
		//for a quiz we want to return the question list not more than the value numberOfQuestion
		//defined in quiz entity
		Set<Question> questions = quiz.getQuestions();
		List<Question> questionList = new ArrayList<>(questions);
		
		if(questionList.size() > quiz.getNumberOfQuestions()) {
			questionList = questionList.subList(0, quiz.getNumberOfQuestions()+1);
		}
		Collections.shuffle(questionList);
		return ResponseEntity.ok(questionList);
	}
	
	@PostMapping
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.createQuestion(question));
	}
	
	@PutMapping
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}
	
	@GetMapping()
	public ResponseEntity<List<Question>> getAllQuestions() {
		return ResponseEntity.ok(questionService.getQuestions());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
		Question existingQuestion;
		try {
			existingQuestion = questionService.getQuestion(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question with this id not found!");
		}
		return ResponseEntity.ok(existingQuestion);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
		try {
			questionService.deleteQuestionById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question not found!");
		}
		return ResponseEntity.ok("Question is deleted successfully");
	}
	
}
