package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.QuestionEntity;

@Component
public class QuestionEntityDaoImpl extends QueryExecutorImpl implements QuestionEntityDao {

   public QuestionEntity createQuestionEntity(QuestionEntity QuestionEntity){
       QuestionEntity.setId(UUID.randomUUID()); 
       insert(QuestionEntity);
       return QuestionEntity;
   }
   public QuestionEntity updateQuestionEntity(QuestionEntity QuestionEntity){
       update(QuestionEntity);
       return QuestionEntity;
   }
   public void deleteQuestionEntity(QuestionEntity QuestionEntity){
       delete(QuestionEntity);
   }
   public QuestionEntity getQuestionEntityById(UUID QuestionEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(QuestionEntity.class);
	   criteria.add(Restrictions.eq("id", QuestionEntityId));
	   List<QuestionEntity> entities = (List<QuestionEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   @SuppressWarnings("unchecked")
   public List<QuestionEntity> getAllQuestionEntitys(Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(QuestionEntity.class);
       return (List<QuestionEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getQuestionEntitysCount(){
       DetachedCriteria criteria=DetachedCriteria.forClass(QuestionEntity.class);
       return countRows(criteria);
   }
}
