package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.CreateSurveyProject;
import com.servinglynk.hmis.warehouse.core.model.SurveyProject;
import com.servinglynk.hmis.warehouse.core.model.SurveyProjects;
public interface SurveyProjectService {

   SurveyProjects createSurveyProject(UUID surveyid, CreateSurveyProject surveyProject,String caller);
   SurveyProject updateSurveyProject(UUID surveyid, SurveyProject SurveyProject,String caller);
   SurveyProject deleteSurveyProject(UUID SurveyProjectId,String caller);
   SurveyProject getSurveyProjectById(UUID SurveyProjectId);
   SurveyProjects getAllSurveySurveyProjects(UUID surveyid, Integer startIndex, Integer maxItems);
   SurveyProjects getAllSurveyByGlobaProjectId(UUID globalProjectId,Integer startIndex, Integer maxItems);
}
