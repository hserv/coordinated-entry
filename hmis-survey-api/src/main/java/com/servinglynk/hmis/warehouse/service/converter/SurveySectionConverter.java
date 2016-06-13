package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.SurveySection;
import com.servinglynk.hmis.warehouse.model.SurveySectionEntity;
public class SurveySectionConverter{

   public static SurveySectionEntity modelToEntity (SurveySection model ,SurveySectionEntity entity) {
       if(entity==null) entity = new SurveySectionEntity();
       entity.setId(model.getSurveySectionId());
       entity.setSectionText(model.getSectionText());
       entity.setSectionDetail(model.getSectionDetail());
       entity.setSectionweight(model.getSectionWeight());
       entity.setOrder(model.getOrder());
//       entity.setSurveyId(model.getSurveyId());
       return entity;    
   }


   public static SurveySection entityToModel (SurveySectionEntity entity) {
       SurveySection model = new SurveySection();
       model.setSurveySectionId(entity.getId());
       model.setSectionText(entity.getSectionText());
       model.setSectionDetail(entity.getSectionDetail());
       model.setSectionWeight(entity.getSectionweight());
       model.setOrder(entity.getOrder());
 //      model.setSurveyId(entity.getSurveyId());
       return model;
   }


}
