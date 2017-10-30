package com.servinglynk.hmis.warehouse.service.converter; 

import java.security.InvalidParameterException;

import com.servinglynk.hmis.warehouse.core.model.Question;
import com.servinglynk.hmis.warehouse.core.model.Questionv2;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.util.QuestionDataTypeEnum;
import com.servinglynk.hmis.warehouse.util.QuestionTypeEnum;
public class QuestionConverterv2   {

   public static QuestionEntity modelToEntity (Questionv2 model ,QuestionEntity entity) {
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
       entity.setPicklistValues(model.getPickListValues());
      
       return entity;    
   }


   public static Questionv2 entityToModel (QuestionEntity entity) {
       Questionv2 model = new Questionv2();
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
       

       if(entity.getQuestionGroupEntity()!=null)
    	   model.setQuestionGroupId(entity.getQuestionGroupEntity().getId());
       
       if(entity.getPicklistValues()!=null) model.setPickListValues(entity.getPicklistValues());
       return model;
   }


}
