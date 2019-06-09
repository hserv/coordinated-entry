package com.servinglynk.hmis.warehouse.dao;


import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
	   DetachedCriteria criteria = DetachedCriteria.forClass(ResponseEntity.class);
	   criteria.add(Restrictions.eq("id", ResponseEntityId));
	   List<ResponseEntity> entities = (List<ResponseEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
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
public ResponseEntity getResponseBySubmission(UUID submissionId, UUID responseId) {
    DetachedCriteria criteria=DetachedCriteria.forClass(ResponseEntity.class);

	return null;
}
public List<ResponseEntity> getAllSubmissionResponses(UUID surveyId, UUID submissionId, Integer startIndex,
		Integer maxItems) {
    DetachedCriteria criteria=DetachedCriteria.forClass(ResponseEntity.class);
    criteria.createAlias("surveyEntity", "surveyEntity");
    criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
    criteria.add(Restrictions.eq("submissionId", submissionId));
    if(startIndex!=null && maxItems!=null)
    	return (List<ResponseEntity>) findByCriteria(criteria,startIndex,maxItems);
    else
    	return (List<ResponseEntity>) findByCriteria(criteria);
}
public long getSubmissionResponsesCount(UUID surveyId, UUID submissionId) {
    DetachedCriteria criteria=DetachedCriteria.forClass(ResponseEntity.class);
    criteria.createAlias("surveyEntity", "surveyEntity");
    criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
    criteria.add(Restrictions.eq("submissionId", submissionId));
	return countRows(criteria);
}
	public ResponseEntity getResponseBySubmission(UUID submissionId) {
	    DetachedCriteria criteria=DetachedCriteria.forClass(ResponseEntity.class);
	    criteria.add(Restrictions.eq("submissionId", submissionId));
	    List<ResponseEntity> responseEntities = (List<ResponseEntity>) findByCriteria(criteria);
	    if(responseEntities.isEmpty()) return null;
	    return responseEntities.get(0);
	}   
	
	public LocalDateTime getSurveyDate(UUID clientId, UUID surveyId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ResponseEntity.class);
		criteria.createAlias("surveyEntity", "surveyEntity");
		criteria.add(Restrictions.eq("clientId", clientId));
		criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
		criteria.addOrder(Order.asc("effectiveDate"));
		List<ResponseEntity> entities =(List<ResponseEntity>) findByCriteria(criteria);
		if(!entities.isEmpty()) return entities.get(0).getEffectiveDate();
		return null;
	}
}
