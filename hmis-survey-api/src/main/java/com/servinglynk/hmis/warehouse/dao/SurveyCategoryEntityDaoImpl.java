package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.SurveyCategoryEntity;

@Component
public class SurveyCategoryEntityDaoImpl extends QueryExecutorImpl implements SurveyCategoryEntityDao {

   public SurveyCategoryEntity createSurveyCategoryEntity(SurveyCategoryEntity SurveyCategoryEntity){
       SurveyCategoryEntity.setId(UUID.randomUUID()); 
       insert(SurveyCategoryEntity);
       return SurveyCategoryEntity;
   }
   public SurveyCategoryEntity updateSurveyCategoryEntity(SurveyCategoryEntity SurveyCategoryEntity){
       update(SurveyCategoryEntity);
       return SurveyCategoryEntity;
   }
   public void deleteSurveyCategoryEntity(SurveyCategoryEntity SurveyCategoryEntity){
       delete(SurveyCategoryEntity);
   }
   public SurveyCategoryEntity getSurveyCategoryEntityById(UUID SurveyCategoryEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(SurveyCategoryEntity.class);
	   criteria.add(Restrictions.eq("id", SurveyCategoryEntityId));
	   List<SurveyCategoryEntity> entities = (List<SurveyCategoryEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   @SuppressWarnings("unchecked")
   public List<SurveyCategoryEntity> getAllSurveySurveyCategoryEntities(UUID surveyId,Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyCategoryEntity.class);
       criteria.createAlias("surveyEntity", "surveyEntity");
       criteria.add(Restrictions.eq("surveyEntity.id", surveyId));     
       return (List<SurveyCategoryEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getSurveyCategoryEntitiesCount(UUID surveyId){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyCategoryEntity.class);
       criteria.createAlias("surveyEntity", "surveyEntity");
       criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
       return countRows(criteria);
   }
}
