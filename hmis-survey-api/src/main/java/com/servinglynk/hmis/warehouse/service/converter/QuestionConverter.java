package com.servinglynk.hmis.warehouse.service.converter; 

import java.security.InvalidParameterException;

import com.servinglynk.hmis.warehouse.core.model.Question;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.util.QuestionDataTypeEnum;
import com.servinglynk.hmis.warehouse.util.QuestionTypeEnum;
public class QuestionConverter   {

   public static QuestionEntity modelToEntity (Question model ,QuestionEntity entity) {
       if(entity==null) entity = new QuestionEntity();
       entity.setId(model.getQuestionId());
       entity.setProjectGroupId(model.getProjectGroupId());
       entity.setQuestionDescription(model.getQuestionDescription());
       entity.setDisplayText(model.getDisplayText());
       entity.setQuestionDataType(model.getQuestionDataType().name());
       entity.setQuestionType(model.getQuestionType().name());
       entity.setCorrectValueForAssessment(model.getCorrectValueForAssessment());
       entity.setCopyQuestionId(model.getCopyQuestionId());
       entity.setHudQuestion(model.getHudQuestion());
       entity.setLocked(model.getLocked());
       entity.setQuestionWeight(model.getQuestionWeight());
       entity.setVisibility(true);
       if(QuestionTypeEnum.isPickListType(model.getQuestionType().getValue())){
    	  if(model.getPickListGroupId()==null) throw new InvalidParameterException("Picklist Group required");
       }
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
       model.setQuestionDataType(QuestionDataTypeEnum.valueOf(entity.getQuestionDataType()));
       model.setQuestionType(QuestionTypeEnum.valueOf(entity.getQuestionType()));
       model.setCorrectValueForAssessment(entity.getCorrectValueForAssessment());
       model.setCopyQuestionId(entity.isCopyQuestionId());
       model.setHudQuestion(entity.isHudQuestion());
       model.setLocked(entity.isLocked());
       model.setQuestionWeight(entity.getQuestionWeight());
       
       if(entity.getPickListGroupEntity()!=null)
    	   	model.setPickListGroupId(entity.getPickListGroupEntity().getId());
       if(entity.getQuestionGroupEntity()!=null)
    	   model.setQuestionGroupId(entity.getQuestionGroupEntity().getId());
       
//       model.setQuestionGroupId(entity.getQuestionGroupId());
//       model.setPickListGroupId(entity.getPickListGroupId());
       return model;
   }


}
