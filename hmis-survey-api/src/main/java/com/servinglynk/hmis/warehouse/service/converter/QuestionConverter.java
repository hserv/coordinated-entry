package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.Question;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
public class QuestionConverter   {

   public static QuestionEntity modelToEntity (Question model ,QuestionEntity entity) {
       if(entity==null) entity = new QuestionEntity();
       entity.setId(model.getQuestionId());
       entity.setProjectGroupId(model.getProjectGroupId());
       entity.setQuestionDescription(model.getQuestionDescription());
       entity.setDisplayText(model.getDisplayText());
       entity.setQuestionDataType(model.getQuestionDataType());
       entity.setQuestionType(model.getQuestionType());
       entity.setCorrectValueForAssessment(model.getCorrectValueForAssessment());
       entity.setCopyQuestionId(model.getCopyQuestionId());
       entity.setHudQuestion(model.getHudQuestion());
       entity.setLocked(model.getLocked());
       entity.setQuestionWeight(model.getQuestionWeight());
//       entity.setQuestionGroupId(model.getQuestionGroupId());
//       entity.setPickListGroupId(model.getPickListGroupId());
       return entity;    
   }


   public static Question entityToModel (QuestionEntity entity) {
       Question model = new Question();
       model.setQuestionId(entity.getId());
       model.setProjectGroupId(entity.getProjectGroupId());
       model.setQuestionDescription(entity.getQuestionDescription());
       model.setDisplayText(entity.getDisplayText());
       model.setQuestionDataType(entity.getQuestionDataType());
       model.setQuestionType(entity.getQuestionType());
       model.setCorrectValueForAssessment(entity.getCorrectValueForAssessment());
       model.setCopyQuestionId(entity.isCopyQuestionId());
       model.setHudQuestion(entity.isHudQuestion());
       model.setLocked(entity.isLocked());
       model.setQuestionWeight(entity.getQuestionWeight());
//       model.setQuestionGroupId(entity.getQuestionGroupId());
//       model.setPickListGroupId(entity.getPickListGroupId());
       return model;
   }


}
