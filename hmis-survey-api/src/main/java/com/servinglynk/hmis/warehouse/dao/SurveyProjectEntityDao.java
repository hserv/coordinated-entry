package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.SurveyProjectEntity;

import java.util.List;

public interface SurveyProjectEntityDao {

   SurveyProjectEntity createSurveyProjectEntity(SurveyProjectEntity surveyProjectEntity);
   SurveyProjectEntity updateSurveyProjectEntity(SurveyProjectEntity surveyProjectEntity);
   void deleteSurveyProjectEntity(SurveyProjectEntity surveyProjectEntity);
   SurveyProjectEntity getSurveyProjectEntityById(UUID surveyProjectEntityId);
   List<SurveyProjectEntity> getAllSurveySurveyProjectEntities(UUID surveyId, Integer startIndex, Integer maxItems);
   long getSurveyProjectEntitiesCount(UUID surveyId);
   List<SurveyProjectEntity> getAllSurveyByGlobaProjectId(UUID globalProjectId,Integer startIndex, Integer maxItems);
   long getAllSurveyByGlobaProjectIdCount(UUID globalProjectId);
}
