package com.vaibhav.service;

import java.util.List;

import com.vaibhav.entity.Quiz;
import com.vaibhav.entity.Role;

public interface QuizService {
	Quiz createQuiz(Quiz quiz);
	
	Quiz updateQuiz(Quiz quiz);
	
	List<Quiz> getAllQuiz();
	
	Quiz getQuizById(Long id) throws Exception;
	
	void deleteQuizById(Long id) throws Exception;
}
