package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.QuestionGroupEntity;

import java.util.List;

public interface QuestionGroupEntityDao {

   QuestionGroupEntity createQuestionGroupEntity(QuestionGroupEntity QuestionGroupEntity);
   QuestionGroupEntity updateQuestionGroupEntity(QuestionGroupEntity QuestionGroupEntity);
   void deleteQuestionGroupEntity(QuestionGroupEntity QuestionGroupEntity);
   QuestionGroupEntity getQuestionGroupEntityById(UUID QuestionGroupEntityId);
   List<QuestionGroupEntity> getAllQuestionGroupEntitys(Integer startIndex, Integer maxItems);
   long getQuestionGroupEntitysCount();
}
