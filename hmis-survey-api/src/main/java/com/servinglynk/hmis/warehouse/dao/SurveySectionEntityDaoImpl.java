package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.SurveySectionEntity;

@Component
public class SurveySectionEntityDaoImpl extends QueryExecutorImpl implements SurveySectionEntityDao {

   public SurveySectionEntity createSurveySectionEntity(SurveySectionEntity SurveySectionEntity){
       SurveySectionEntity.setId(UUID.randomUUID()); 
       insert(SurveySectionEntity);
       return SurveySectionEntity;
   }
   public SurveySectionEntity updateSurveySectionEntity(SurveySectionEntity SurveySectionEntity){
       update(SurveySectionEntity);
       return SurveySectionEntity;
   }
   public void deleteSurveySectionEntity(SurveySectionEntity SurveySectionEntity){
       delete(SurveySectionEntity);
   }
   public SurveySectionEntity getSurveySectionEntityById(UUID SurveySectionEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(SurveySectionEntity.class);
	   criteria.add(Restrictions.eq("id", SurveySectionEntityId));
	   List<SurveySectionEntity> entities = (List<SurveySectionEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   @SuppressWarnings("unchecked")
   public List<SurveySectionEntity> getAllSurveySurveySectionEntities(UUID surveyId,Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveySectionEntity.class);
       criteria.createAlias("surveyEntity", "surveyEntity");
       criteria.add(Restrictions.eq("surveyEntity.id", surveyId));     
       return (List<SurveySectionEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getSurveySectionEntitiesCount(UUID surveyId){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveySectionEntity.class);
       criteria.createAlias("surveyEntity", "surveyEntity");
       criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
       return countRows(criteria);
   }
}
