package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.CreateSurveyProject;
import com.servinglynk.hmis.warehouse.core.model.SurveyProject;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.model.SurveyProjectEntity;
public class SurveyProjectConverter{

   public static SurveyProjectEntity modelToEntity (SurveyProject model ,SurveyProjectEntity entity) {
       if(entity==null) entity = new SurveyProjectEntity();
       entity.setId(model.getSurveyProjectId());
       entity.setSchemaVersion(model.getSchemaVersion());
       return entity;    
   }


   public static SurveyProject entityToModel (SurveyProjectEntity entity) {
       SurveyProject model = new SurveyProject();
       model.setSurveyProjectId(entity.getId());
       model.setGlobalProjectId(entity.getGlobalProjectId());
       model.setSchemaVersion(entity.getSchemaVersion());
       model.setDateCreated(entity.getCreatedAt());
       model.setDateUpdated(entity.getUpdatedAt());
       SurveyEntity surveyEntity = entity.getSurveyEntity();
       if(surveyEntity !=null) {
    	   model.setSurveyId(surveyEntity.getId());
    	   model.setSurveyTitle(surveyEntity.getSurveyTitle());
       }
       
       return model;
   }
   
   public static SurveyProjectEntity modelToEntity (CreateSurveyProject model ,SurveyProjectEntity entity) {
       if(entity==null) entity = new SurveyProjectEntity();
       entity.setId(model.getSurveyProjectId());
       entity.setSchemaVersion(model.getSchemaVersion());
       return entity;    
   }

}
