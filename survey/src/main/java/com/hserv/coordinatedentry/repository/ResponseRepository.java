package com.hserv.coordinatedentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.entity.ResponseStorage;
import com.hserv.coordinatedentry.entity.Survey;

public interface ResponseRepository extends JpaRepository<ResponseStorage, Integer>{
	
	public List<ResponseStorage> findByClientId(String clientId) ;
	
	//public List<ResponseStorage> findByClientIdAndSurveyId(String clientId, Integer surveyId) ;
	
	public List<ResponseStorage> findByClientIdAndResponseIdAndSurveyId(String clientId, Integer responseId,  Integer surveyId) ;
	
}
