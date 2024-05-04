package com.vaibhav.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.entity.Quiz;
import com.vaibhav.repository.QuizRepository;
import com.vaibhav.service.QuizService;


@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired
	QuizRepository quizRepository;

	@Override
	public Quiz createQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public List<Quiz> getAllQuiz() {
		return quizRepository.findAll();
	}

	@Override
	public Quiz getQuizById(Long id) throws Exception {
		Optional<Quiz> existingQuiz = quizRepository.findById(id);
		if(!existingQuiz.isPresent()) {
			throw new Exception("Quiz not found");
		}
		return existingQuiz.get();
	}

	@Override
	public void deleteQuizById(Long id) throws Exception {
		this.getQuizById(id);
		quizRepository.deleteById(id);
	}
}
