package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.QuestionEntity;

import java.util.List;

public interface QuestionEntityDao {

   QuestionEntity createQuestionEntity(QuestionEntity QuestionEntity);
   QuestionEntity updateQuestionEntity(QuestionEntity QuestionEntity);
   void deleteQuestionEntity(QuestionEntity QuestionEntity);
   QuestionEntity getQuestionEntityById(UUID QuestionEntityId);
   List<QuestionEntity> getAllQuestionEntitys(Integer startIndex, Integer maxItems);
   long getQuestionEntitysCount();
}
