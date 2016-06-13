package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.SurveySection;
import com.servinglynk.hmis.warehouse.core.model.SurveySections;
public interface SurveySectionService {

   SurveySection createSurveySection(UUID surveyid, SurveySection SurveySection,String caller);
   SurveySection updateSurveySection(UUID surveyid, SurveySection SurveySection,String caller);
   SurveySection deleteSurveySection(UUID SurveySectionId,String caller);
   SurveySection getSurveySectionById(UUID SurveySectionId);
   SurveySections getAllSurveySurveySections(UUID surveyid, Integer startIndex, Integer maxItems);
}
