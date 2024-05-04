package com.vaibhav.service;

import java.util.List;

import com.vaibhav.entity.Question;
import com.vaibhav.entity.Quiz;
import com.vaibhav.entity.Role;

public interface QuestionService {
	Question createQuestion(Question question);
	
	Question updateQuestion(Question question);
	
	List<Question> getQuestions();
	
	Question getQuestion(Long id) throws Exception;
	
	List<Question> getQuestionsOfAQuiz(Quiz quiz);
	
	void deleteQuestionById(Long id) throws Exception;
}
