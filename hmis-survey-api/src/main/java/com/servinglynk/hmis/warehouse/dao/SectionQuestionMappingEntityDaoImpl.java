package com.servinglynk.hmis.warehouse.dao;


import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.SectionQuestionMappingEntity;

@Component
public class SectionQuestionMappingEntityDaoImpl extends QueryExecutorImpl implements SectionQuestionMappingEntityDao {

   public SectionQuestionMappingEntity createSectionQuestionMappingEntity(SectionQuestionMappingEntity SectionQuestionMappingEntity){
       SectionQuestionMappingEntity.setId(UUID.randomUUID()); 
       insert(SectionQuestionMappingEntity);
       return SectionQuestionMappingEntity;
   }
   public SectionQuestionMappingEntity updateSectionQuestionMappingEntity(SectionQuestionMappingEntity SectionQuestionMappingEntity){
       update(SectionQuestionMappingEntity);
       return SectionQuestionMappingEntity;
   }
   public void deleteSectionQuestionMappingEntity(SectionQuestionMappingEntity SectionQuestionMappingEntity){
       delete(SectionQuestionMappingEntity);
   }
   public SectionQuestionMappingEntity getSectionQuestionMappingEntityById(UUID SectionQuestionMappingEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(SectionQuestionMappingEntity.class);
	   criteria.add(Restrictions.eq("id", SectionQuestionMappingEntityId));
	   List<SectionQuestionMappingEntity> entities = (List<SectionQuestionMappingEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   
   @SuppressWarnings("unchecked")
   public SectionQuestionMappingEntity getSectionQuestionMappingEntityByQuestionId(UUID questionId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(SectionQuestionMappingEntity.class);
	   criteria.createAlias("questionEntity", "questionEntity");
	   criteria.add(Restrictions.eq("questionEntity.id",questionId));
	   List<SectionQuestionMappingEntity> entities = (List<SectionQuestionMappingEntity>) findByCriteria(criteria);
	   if(!entities.isEmpty()) return entities.get(0);
	   return null;
   }
   
   
   @SuppressWarnings("unchecked")
   public List<SectionQuestionMappingEntity> getAllSectionQuestionMappingEntities(UUID surveysectionid,Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(SectionQuestionMappingEntity.class);
       criteria.createAlias("surveySectionEntity", "surveySectionEntity");
       criteria.add(Restrictions.eq("surveySectionEntity.id",surveysectionid));
       return (List<SectionQuestionMappingEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getSectionQuestionMappingEntitiesCount(UUID surveysectionid){
       DetachedCriteria criteria=DetachedCriteria.forClass(SectionQuestionMappingEntity.class);
       criteria.createAlias("surveySectionEntity", "surveySectionEntity");
       criteria.add(Restrictions.eq("surveySectionEntity.id",surveysectionid));
       return countRows(criteria);
   }
}
