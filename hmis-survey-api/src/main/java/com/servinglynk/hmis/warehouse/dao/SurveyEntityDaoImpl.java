package com.servinglynk.hmis.warehouse.dao;


import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
	   DetachedCriteria criteria = DetachedCriteria.forClass(SurveyEntity.class);
	   criteria.add(Restrictions.eq("id", surveyEntityId));
	   List<SurveyEntity> entities = (List<SurveyEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   @SuppressWarnings("unchecked")
   public List<SurveyEntity> getAllSurveyEntitys(Integer startIndex, Integer maxItems,String projectGroupCode){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyEntity.class);
       criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
       return (List<SurveyEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getSurveyEntitysCount(String projectGroupCode){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyEntity.class);
       criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
       return countRows(criteria);
   }
}
