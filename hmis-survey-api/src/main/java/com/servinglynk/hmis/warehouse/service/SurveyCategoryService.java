package com.servinglynk.hmis.warehouse.service; 

import java.util.Set;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.SurveyCategory;
public interface SurveyCategoryService {

   void createSurveyCategory(UUID surveyid, Set<SurveyCategory> surveyCategories,UUID caller);
}