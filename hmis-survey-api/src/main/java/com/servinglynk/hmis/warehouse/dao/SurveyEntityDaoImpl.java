package com.servinglynk.hmis.warehouse.dao;


import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.SurveyEntity;

@Component
public class SurveyEntityDaoImpl extends QueryExecutorImpl implements SurveyEntityDao {

   public SurveyEntity createSurveyEntity(SurveyEntity surveyEntity){
       surveyEntity.setId(UUID.randomUUID()); 
       insert(surveyEntity);
       return surveyEntity;
   }
   public SurveyEntity updateSurveyEntity(SurveyEntity surveyEntity){
       update(surveyEntity);
       return surveyEntity;
   }
   public void deleteSurveyEntity(SurveyEntity surveyEntity){
       delete(surveyEntity);
   }
   public SurveyEntity getSurveyEntityById(UUID surveyEntityId){ 
       return (SurveyEntity) get(SurveyEntity.class, surveyEntityId);
   }
   @SuppressWarnings("unchecked")
   public List<SurveyEntity> getAllSurveyEntitys(Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyEntity.class);
       return (List<SurveyEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getSurveyEntitysCount(){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyEntity.class);
       return countRows(criteria);
   }
}
