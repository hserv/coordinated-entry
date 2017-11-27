package com.servinglynk.hmis.warehouse.service.converter;

import com.servinglynk.hmis.warehouse.core.model.SectionScore;
import com.servinglynk.hmis.warehouse.model.ClientEntity;
import com.servinglynk.hmis.warehouse.model.SectionScoreEntity;

public class SectionScoreConverterV3 {

	public static SectionScore entityToModel(SectionScoreEntity sectionScoreEntity,ClientEntity clientEntity){
		SectionScore sectionScore = new SectionScore();
			sectionScore.setSectionScore(sectionScoreEntity.getSectionScore());
			sectionScore.setSectionId(sectionScoreEntity.getSectionEntity().getId());
			sectionScore.setClientId(clientEntity.getId());
			sectionScore.setSectionScoreId(sectionScoreEntity.getId());
			sectionScore.setSurveyId(sectionScoreEntity.getSurveyEntity().getId());		
			sectionScore.setClientDedupId(clientEntity.getDedupClientId());
		return sectionScore;
	}
	
}