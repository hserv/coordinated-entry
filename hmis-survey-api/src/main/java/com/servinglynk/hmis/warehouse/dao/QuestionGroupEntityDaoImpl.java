package com.servinglynk.hmis.warehouse.dao;


import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
	   DetachedCriteria criteria = DetachedCriteria.forClass(QuestionGroupEntity.class);
	   criteria.add(Restrictions.eq("id", QuestionGroupEntityId));
	   List<QuestionGroupEntity> entities = (List<QuestionGroupEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
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
