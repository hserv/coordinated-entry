package com.hserv.coordinatedentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.entity.Survey;

@Component
public interface SurveyRepository extends JpaRepository<Survey, Integer>{

	
	public List<Survey> findBySurveyTitleContainingAllIgnoringCase(String title) ;
	public Survey findBySurveyIdAndUserId(Integer surveyId,String userId) ;
	
	
}
