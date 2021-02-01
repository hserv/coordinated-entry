package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.SurveyProjectEntity;

@Component
public class SurveyProjectEntityDaoImpl extends QueryExecutorImpl implements SurveyProjectEntityDao {

   public SurveyProjectEntity createSurveyProjectEntity(SurveyProjectEntity SurveyProjectEntity){
       SurveyProjectEntity.setId(UUID.randomUUID()); 
       insert(SurveyProjectEntity);
       return SurveyProjectEntity;
   }
   public SurveyProjectEntity updateSurveyProjectEntity(SurveyProjectEntity SurveyProjectEntity){
       update(SurveyProjectEntity);
       return SurveyProjectEntity;
   }
   public void deleteSurveyProjectEntity(SurveyProjectEntity SurveyProjectEntity){
       delete(SurveyProjectEntity);
   }
   public SurveyProjectEntity getSurveyProjectEntityById(UUID SurveyProjectEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(SurveyProjectEntity.class);
	   criteria.add(Restrictions.eq("id", SurveyProjectEntityId));
	   List<SurveyProjectEntity> entities = (List<SurveyProjectEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   @SuppressWarnings("unchecked")
   public List<SurveyProjectEntity> getAllSurveySurveyProjectEntities(UUID surveyId,Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyProjectEntity.class);
       criteria.createAlias("surveyEntity", "surveyEntity");
       criteria.add(Restrictions.eq("surveyEntity.id", surveyId));     
       return (List<SurveyProjectEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getSurveyProjectEntitiesCount(UUID surveyId){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyProjectEntity.class);
       criteria.createAlias("surveyEntity", "surveyEntity");
       criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
       return countRows(criteria);
   }
   
   @SuppressWarnings("unchecked")
   public List<SurveyProjectEntity> getAllSurveyByGlobaProjectId(UUID globalProjectId,Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyProjectEntity.class);
       criteria.add(Restrictions.eq("globalProjectId", globalProjectId));     
       return (List<SurveyProjectEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   
   public long getAllSurveyByGlobaProjectIdCount(UUID globalProjectId){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyProjectEntity.class);
       criteria.add(Restrictions.eq("globalProjectId", globalProjectId)); 
       return countRows(criteria);
   }
}
