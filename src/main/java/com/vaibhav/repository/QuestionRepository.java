package com.vaibhav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.entity.Question;
import com.vaibhav.entity.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

//	List<Question> findByQuiz(Quiz quiz);

}
