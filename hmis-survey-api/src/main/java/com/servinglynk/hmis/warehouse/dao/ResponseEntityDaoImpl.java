package com.servinglynk.hmis.warehouse.dao;


import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.ResponseEntity;

@Component
public class ResponseEntityDaoImpl extends QueryExecutorImpl implements ResponseEntityDao {

   public ResponseEntity createResponseEntity(ResponseEntity ResponseEntity){
       ResponseEntity.setId(UUID.randomUUID()); 
       insert(ResponseEntity);
       return ResponseEntity;
   }
   public ResponseEntity updateResponseEntity(ResponseEntity ResponseEntity){
       update(ResponseEntity);
       return ResponseEntity;
   }
   public void deleteResponseEntity(ResponseEntity ResponseEntity){
       delete(ResponseEntity);
   }
   public ResponseEntity getResponseEntityById(UUID ResponseEntityId){ 
       return (ResponseEntity) get(ResponseEntity.class, ResponseEntityId);
   }
   
   @SuppressWarnings("unchecked")
   public List<ResponseEntity> getAllSurveyResponses(UUID surveyid,UUID sectionId,Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(ResponseEntity.class);
       criteria.createAlias("surveyEntity", "surveyEntity");
       criteria.add(Restrictions.eq("surveyEntity.id", surveyid));
       
      // criteria.createAlias("surveySectionEntity", "surveySectionEntity");
      // criteria.add(Restrictions.eq("surveySectionEntity.id", sectionId));
       
       if(startIndex==0 && maxItems==0)        return (List<ResponseEntity>) findByCriteria(criteria);
       
       return (List<ResponseEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getSurveyResponsesCount(UUID surveyid){
       DetachedCriteria criteria=DetachedCriteria.forClass(ResponseEntity.class);
       criteria.createAlias("surveyEntity", "surveyEntity");
       criteria.add(Restrictions.eq("surveyEntity.id", surveyid));
       return countRows(criteria);
   }   
}
