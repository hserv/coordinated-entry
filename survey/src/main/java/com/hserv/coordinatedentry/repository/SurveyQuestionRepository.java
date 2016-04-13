package com.hserv.coordinatedentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.entity.SurveyQuestion;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Integer>{

	//public List<SurveyQuestion> findByQuestions
	
}
