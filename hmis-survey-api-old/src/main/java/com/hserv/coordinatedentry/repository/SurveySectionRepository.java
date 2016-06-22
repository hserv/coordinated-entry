package com.hserv.coordinatedentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.entity.SurveySection;

public interface SurveySectionRepository extends JpaRepository<SurveySection, Integer>{

	public List<SurveySection> findBySurveyId(Integer surveyId);
	
}
