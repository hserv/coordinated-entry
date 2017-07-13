package com.servinglynk.hmis.warehouse.service;

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.ClientsSurveyScores;
import com.servinglynk.hmis.warehouse.core.model.SectionScore;
import com.servinglynk.hmis.warehouse.core.model.SectionScores;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.model.ResponseEntity;

public interface SectionScoreService {

	SectionScores getAllSectionScores(UUID clientId,UUID surveyId,UUID sectionId,Integer startIndex,Integer maxItems);
	 int calculateQuestionScore(QuestionEntity questionEntity,ResponseEntity responseEntity);
	 
	 void updateSectionScores(UUID clientId,UUID surveyId,UUID sectionId, String caller);
	 void deleteSectionScores(UUID clientId,UUID surveyId,UUID sectionId);
	 ClientsSurveyScores calculateClientSurveyScore(Integer startIndex,Integer maxItems,String projectGroupCode);
	 SectionScore createSectionScores(SectionScore sectionScore, Session session);
	void updateSectionScores(SectionScore sectionScore,Session session);
}