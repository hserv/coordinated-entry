package com.servinglynk.hmis.warehouse.service.converter;

import com.servinglynk.hmis.warehouse.core.model.SectionScore;
import com.servinglynk.hmis.warehouse.model.SectionScoreEntity;

public class SectionScoreConverter {

	public static SectionScore entityToModel(SectionScoreEntity sectionScoreEntity){
		SectionScore sectionScore = new SectionScore();
			sectionScore.setSectionScore(sectionScoreEntity.getSectionScore());
			sectionScore.setSectionId(sectionScoreEntity.getSectionEntity().getId());
			sectionScore.setClientId(sectionScoreEntity.getClientId());
			sectionScore.setSectionScoreId(sectionScoreEntity.getId());
			sectionScore.setSurveyId(sectionScoreEntity.getSurveyEntity().getId());		
		return sectionScore;
	}
	
}