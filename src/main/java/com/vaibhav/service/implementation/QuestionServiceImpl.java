package com.vaibhav.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.entity.Question;
import com.vaibhav.entity.Quiz;
import com.vaibhav.repository.QuestionRepository;
import com.vaibhav.service.QuestionService;


@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question createQuestion(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public List<Question> getQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public Question getQuestion(Long id) throws Exception {
		Optional<Question> existingQuestion = questionRepository.findById(id);
		System.out.println("getting quesiotn");
		if(!existingQuestion.isPresent()) {
			throw new Exception("question not found!");
		}
		System.out.println("question found with id: " + existingQuestion.get().getId());
		return existingQuestion.get();
	}

	@Override
	public List<Question> getQuestionsOfAQuiz(Quiz quiz) {
//		return questionRepository.findByQuiz(quiz);
		return null;
	}

	@Override
	public void deleteQuestionById(Long id) throws Exception {
		this.getQuestion(id);
		questionRepository.deleteById(id);
	}

}
