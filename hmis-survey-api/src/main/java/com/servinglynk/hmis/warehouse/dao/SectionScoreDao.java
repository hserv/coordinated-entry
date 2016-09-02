package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveyScore;
import com.servinglynk.hmis.warehouse.model.SectionScoreEntity;

public interface SectionScoreDao {
	SectionScoreEntity createSectionScore(SectionScoreEntity sectionScoreEntity);
	void deleteSectionScore(SectionScoreEntity sectionScoreEntity);   
	List<SectionScoreEntity> getAllSectionScores(UUID surveyId,UUID sectionId,Integer startIndex,Integer maxItems);
	long getAllSectionScoresCount(UUID surveyId,UUID sectionId);
//	List<ClientSurveyScore> calculateClientSurveyScore(List<UUID> clientIds);
	SectionScoreEntity getSectionScoreById(UUID sectionScoreId);
	void updateSectionScore(SectionScoreEntity sectionScoreEntity);
	List<ClientSurveyScore> calculateClientSurveyScore(Integer startIndex,Integer maxItems,String projectGroupCode);
	Long countOfClientSurveyScore(String projectGroupCode);
}