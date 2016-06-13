package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.QuestionGroup;
import com.servinglynk.hmis.warehouse.model.QuestionGroupEntity;
public class QuestionGroupConverter{

   public static QuestionGroupEntity modelToEntity (QuestionGroup model ,QuestionGroupEntity entity) {
       if(entity==null) entity = new QuestionGroupEntity();
       entity.setId(model.getQuestionIGroupId());
       entity.setQuestionGroupName(model.getQuestionGroupName());
       return entity;    
   }


   public static QuestionGroup entityToModel (QuestionGroupEntity entity) {
       QuestionGroup model = new QuestionGroup();
       model.setQuestionIGroupId(entity.getId());
       model.setQuestionGroupName(entity.getQuestionGroupName());
       return model;
   }


}
