package com.servinglynk.hmis.warehouse.service; 

import java.util.Set;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.SurveyCategories;
import com.servinglynk.hmis.warehouse.core.model.SurveyCategory;
public interface SurveyCategoryService {

   void createSurveyCategory(UUID surveyid, Set<SurveyCategory> surveyCategories);
   SurveyCategory createSurveyCategory(UUID surveyid, SurveyCategory surveyCategory, String caller);
   SurveyCategory deleteSurveyCategory(UUID surveyCategoryId,String caller);
   SurveyCategory updateSurveyCategory(UUID surveyCategoryId,SurveyCategory surveyCategory, String caller);
   SurveyCategory getSurveyCategoryById(UUID surveyCategoryId);
   SurveyCategories getAllSurveySurveyCategories(UUID surveyId,Integer startIndex, Integer maxItems);
}
