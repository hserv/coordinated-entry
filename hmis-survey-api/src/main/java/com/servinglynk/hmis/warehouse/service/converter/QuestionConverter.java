package com.servinglynk.hmis.warehouse.service.converter; 

import java.security.InvalidParameterException;

import org.apache.commons.lang.StringUtils;

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
       if(model.getUpdateUrlTemplate()!=null) entity.setUpdateUrlTemplate(model.getUpdateUrlTemplate());
       if(model.getUriObjectField()!=null) entity.setUriObjectField(model.getUriObjectField());
       if(model.getQuestionClassification() != null)  {
    	   entity.setQuestionClassification(model.getQuestionClassification());
    	   if(StringUtils.equals(model.getQuestionClassification(), "HUD")) {
    		   entity.setHudQuestion(true);  
    	   }
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
       try {
    	   if(entity.getQuestionDataType()!=null) model.setQuestionDataType(QuestionDataTypeEnum.valueOf(entity.getQuestionDataType()));
       }catch (Exception e) {

       }	
    
       model.setQuestionType(QuestionTypeEnum.valueOf(entity.getQuestionType()));
       model.setCorrectValueForAssessment(entity.getCorrectValueForAssessment());
       model.setCopyQuestionId(entity.isCopyQuestionId());
       model.setHudQuestion(entity.isHudQuestion());
       model.setLocked(entity.isLocked());
       model.setQuestionWeight(entity.getQuestionWeight());
       model.setPickListValues(entity.getPicklistValues());
       if(entity.getPickListGroupEntity()!=null)
    	   	model.setPickListGroupId(entity.getPickListGroupEntity().getId());
       if(entity.getQuestionGroupEntity()!=null)
    	   model.setQuestionGroupId(entity.getQuestionGroupEntity().getId());
       
       if(entity.getQuestionClassification() != null) {
    	   model.setQuestionClassification(model.getQuestionClassification());
    	   if(StringUtils.equals(entity.getQuestionClassification(), "HUD")) {
    		   model.setHudQuestion(true);  
    	   }
       }
       
       if(entity.getUpdateUrlTemplate()!=null) model.setUpdateUrlTemplate(entity.getUpdateUrlTemplate());
       if(entity.getUriObjectField()!=null) model.setUriObjectField(entity.getUriObjectField());
//       model.setQuestionGroupId(entity.getQuestionGroupId());
//       model.setPickListGroupId(entity.getPickListGroupId());
       return model;
   }


}
