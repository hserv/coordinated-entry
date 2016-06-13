package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.SectionQuestionMapping;
import com.servinglynk.hmis.warehouse.model.SectionQuestionMappingEntity;
public class SectionQuestionMappingConverter{

   public static SectionQuestionMappingEntity modelToEntity (SectionQuestionMapping model ,SectionQuestionMappingEntity entity) {
       if(entity==null) entity = new SectionQuestionMappingEntity();
//       entity.setQuestionId(model.getQuestionId());
//       entity.setQuestionGropId(model.getQuestionGropId());
//       entity.setSectionId(model.getSectionId());
       return entity;    
   }


   public static SectionQuestionMapping entityToModel (SectionQuestionMappingEntity entity) {
       SectionQuestionMapping model = new SectionQuestionMapping();
       model.setQuestion(QuestionConverter.entityToModel(entity.getQuestionEntity()));
       model.setSurveySection(SurveySectionConverter.entityToModel(entity.getSurveySectionEntity()));
       model.setRequired(entity.isRequired());
       return model;
   }


}
