package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.SurveyEntity;

import java.util.List;

public interface SurveyEntityDao {

   SurveyEntity createSurveyEntity(SurveyEntity surveyEntity);
   SurveyEntity updateSurveyEntity(SurveyEntity surveyEntity);
   void deleteSurveyEntity(SurveyEntity surveyEntity);
   SurveyEntity getSurveyEntityById(UUID surveyEntityId);
   List<SurveyEntity> getAllSurveyEntitys(Integer startIndex, Integer maxItems, String projectGroupCode);
   long getSurveyEntitysCount(String projectGroupCode);
}
