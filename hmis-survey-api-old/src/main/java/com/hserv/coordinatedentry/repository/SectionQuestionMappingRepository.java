package com.hserv.coordinatedentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.entity.SectionQuestionMapping;
import com.hserv.coordinatedentry.entity.SurveySection;

@Repository
public interface SectionQuestionMappingRepository extends
		JpaRepository<SectionQuestionMapping, Integer> {

	@Query("select sqm from SectionQuestionMapping sqm where sqm.question.questionId = :qid")
	public List<SectionQuestionMapping> getAllSectionQuestionMapping(@Param("qid") Integer questionId);
	
	public void deleteBySurveySectionIn(List<SurveySection> surveySections);

}
