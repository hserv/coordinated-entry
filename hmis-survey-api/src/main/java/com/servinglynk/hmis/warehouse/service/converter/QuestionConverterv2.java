package com.servinglynk.hmis.warehouse.service.converter; 

import org.apache.commons.lang.StringUtils;

import com.servinglynk.hmis.warehouse.core.model.Questionv2;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
public class QuestionConverterv2   {

   public static QuestionEntity modelToEntity (Questionv2 model ,QuestionEntity entity) {
       if(entity==null) entity = new QuestionEntity();
  //     entity.setId(model.getQuestionId());
       if(model.getProjectGroupId()!=null)entity.setProjectGroupId(model.getProjectGroupId());
       if(model.getQuestionDescription()!=null)entity.setQuestionDescription(model.getQuestionDescription());
       if(model.getDisplayText()!=null)entity.setDisplayText(model.getDisplayText());
       if(model.getQuestionDataType()!=null)entity.setQuestionDataType(model.getQuestionDataType());
       if(model.getQuestionType()!=null)entity.setQuestionType(model.getQuestionType());
       if(model.getCorrectValueForAssessment()!=null)entity.setCorrectValueForAssessment(model.getCorrectValueForAssessment());
       entity.setCopyQuestionId(model.getCopyQuestionId());
       entity.setHudQuestion(model.getHudQuestion());
       entity.setLocked(model.getLocked());
       entity.setQuestionWeight(model.getQuestionWeight());
       if(model.getPickListValues()!=null)entity.setPicklistValues(model.getPickListValues());
       if(model.getDefinition()!=null)entity.setDefinition(model.getDefinition());
       if(model.isVisibility()!=null)entity.setVisibility(model.isVisibility());
       if(model.isVisibility()==null)entity.setVisibility(false);
       if(model.getCategory()!=null)entity.setCategory(model.getCategory());
       if(model.getSubcategory()!=null)entity.setSubcategory(model.getSubcategory());
       if(model.getUpdateUrlTemplate()!=null) entity.setUpdateUrlTemplate(model.getUpdateUrlTemplate());
       if(model.getUriObjectField()!=null) entity.setUriObjectField(model.getUriObjectField());
       if(model.getQuestionClassification() != null)  {
    	   entity.setQuestionClassification(model.getQuestionClassification());
    	   if(StringUtils.equals(model.getQuestionClassification(), "HUD")) {
    		   entity.setHudQuestion(true);  
    	   }
       }
       return entity;    
   }


   public static Questionv2 entityToModel (QuestionEntity entity) {
       Questionv2 model = new Questionv2();
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
       model.setDefinition(entity.getDefinition());
       model.setQuestionClassification(entity.getQuestionClassification());
       model.setVisibility(entity.isVisibility());
       model.setCategory(entity.getCategory());
       model.setSubcategory(entity.getSubcategory());
       if(entity.getQuestionClassification() != null) {
    	   model.setQuestionClassification(model.getQuestionClassification());
    	   if(StringUtils.equals(entity.getQuestionClassification(), "HUD")) {
    		   model.setHudQuestion(true);  
    	   }
       }
       
       if(entity.getQuestionGroupEntity()!=null)
    	   model.setQuestionGroupId(entity.getQuestionGroupEntity().getId());
       
       if(entity.getPicklistValues()!=null) model.setPickListValues(entity.getPicklistValues());
       if(entity.getUpdateUrlTemplate()!=null) model.setUpdateUrlTemplate(entity.getUpdateUrlTemplate());
       if(entity.getUriObjectField()!=null) model.setUriObjectField(entity.getUriObjectField());
       return model;
   }


}
