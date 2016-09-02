package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.Survey;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
public class SurveyConverter{

   public static SurveyEntity modelToEntity (Survey model ,SurveyEntity entity) {
       if(entity==null) entity = new SurveyEntity();
       entity.setId(model.getSurveyId());
       entity.setSurveyTitle(model.getSurveyTitle());
       entity.setSurveyOwner(model.getSurveyOwner());
       entity.setTagValue(model.getTagValue());
       entity.setLocked(model.getLocked());
       entity.setCopySurveyId(model.getCopySurveyId());
       entity.setLocked(model.getLocked());
       return entity;    
   }


   public static Survey entityToModel (SurveyEntity entity) {
       Survey model = new Survey();
       model.setSurveyId(entity.getId());
       model.setSurveyTitle(entity.getSurveyTitle());
       model.setSurveyOwner(entity.getSurveyOwner());
       model.setTagValue(entity.getTagValue());
       model.setProjectGroupCode(entity.getProjectGroupCode());
       model.setLocked(entity.isLocked());
       model.setCopySurveyId(entity.isCopySurveyId());
       return model;
   }


}
