package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.Surveyv2;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
public class SurveyConverterv2{

   public static SurveyEntity modelToEntity (Surveyv2 model ,SurveyEntity entity) {
       if(entity==null) entity = new SurveyEntity();
       entity.setId(model.getSurveyId());
       entity.setSurveyTitle(model.getSurveyTitle());
       entity.setSurveyOwner(model.getSurveyOwner());
       entity.setTagValue(model.getTagValue());
       entity.setLocked(model.getLocked());
       entity.setCopySurveyId(model.getCopySurveyId());
       entity.setLocked(model.getLocked());
       entity.setSurveyDefinition(model.getSurveyDefinition()); 
       return entity;    
   }


   public static Surveyv2 entityToModel (SurveyEntity entity) {
       Surveyv2 model = new Surveyv2();
       model.setSurveyId(entity.getId());
       model.setSurveyTitle(entity.getSurveyTitle());
       model.setSurveyOwner(entity.getSurveyOwner());
       model.setTagValue(entity.getTagValue());
       model.setProjectGroupCode(entity.getProjectGroupCode());
       model.setLocked(entity.isLocked());
       model.setCopySurveyId(entity.isCopySurveyId());
       model.setSurveyDefinition(entity.getSurveyDefinition());
       return model;
   }
}