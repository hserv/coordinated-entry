package com.servinglynk.hmis.warehouse.dao;


import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.QuestionGroupEntity;

@Component
public class QuestionGroupEntityDaoImpl extends QueryExecutorImpl implements QuestionGroupEntityDao {

   public QuestionGroupEntity createQuestionGroupEntity(QuestionGroupEntity QuestionGroupEntity){
       QuestionGroupEntity.setId(UUID.randomUUID()); 
       insert(QuestionGroupEntity);
       return QuestionGroupEntity;
   }
   public QuestionGroupEntity updateQuestionGroupEntity(QuestionGroupEntity QuestionGroupEntity){
       update(QuestionGroupEntity);
       return QuestionGroupEntity;
   }
   public void deleteQuestionGroupEntity(QuestionGroupEntity QuestionGroupEntity){
       delete(QuestionGroupEntity);
   }
   public QuestionGroupEntity getQuestionGroupEntityById(UUID QuestionGroupEntityId){ 
       return (QuestionGroupEntity) get(QuestionGroupEntity.class, QuestionGroupEntityId);
   }
   @SuppressWarnings("unchecked")
   public List<QuestionGroupEntity> getAllQuestionGroupEntitys(Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(QuestionGroupEntity.class);
       return (List<QuestionGroupEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getQuestionGroupEntitysCount(){
       DetachedCriteria criteria=DetachedCriteria.forClass(QuestionGroupEntity.class);
       return countRows(criteria);
   }
}
