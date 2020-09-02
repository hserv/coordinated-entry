package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.SurveyCategory;
import com.servinglynk.hmis.warehouse.model.SurveyCategoryEntity;
public class SurveyCategoryConverter{

   public static SurveyCategoryEntity modelToEntity (SurveyCategory model ,SurveyCategoryEntity entity) {
       if(entity==null) entity = new SurveyCategoryEntity();
       entity.setId(model.getSurveyCategoryId());
       entity.setSurveyCategory(model.getCategory());
       return entity;    
   }


   public static SurveyCategory entityToModel (SurveyCategoryEntity entity) {
       SurveyCategory model = new SurveyCategory();
       model.setSurveyCategoryId(entity.getId());
       model.setSurveyId(entity.getSurveyEntity().getId());
       model.setSurveyCategoryId(entity.getId());
       model.setCategory(entity.getSurveyCategory());
       model.setDateCreated(entity.getCreatedAt());
       model.setDateUpdated(entity.getUpdatedAt());
       return model;
   }

}
