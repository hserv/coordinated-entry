package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.PickListGroupEntity;
import com.servinglynk.hmis.warehouse.model.PickListValueEntity;

@Component
public class PickListValueEntityDaoImpl extends QueryExecutorImpl implements PickListValueEntityDao {

   public PickListValueEntity createPickListValueEntity(PickListValueEntity PickListValueEntity){
       PickListValueEntity.setId(UUID.randomUUID()); 
       insert(PickListValueEntity);
       return PickListValueEntity;
   }
   public PickListValueEntity updatePickListValueEntity(PickListValueEntity PickListValueEntity){
       update(PickListValueEntity);
       return PickListValueEntity;
   }
   public void deletePickListValueEntity(PickListValueEntity PickListValueEntity){
       delete(PickListValueEntity);
   }
   public PickListValueEntity getPickListValueEntityById(UUID PickListValueEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(PickListValueEntity.class);
	   criteria.add(Restrictions.eq("id", PickListValueEntityId));
	   List<PickListValueEntity> entities = (List<PickListValueEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   @SuppressWarnings("unchecked")
   public List<PickListValueEntity> getAllPickListGroupPickListValueEntities(UUID pickListGroupId,Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(PickListValueEntity.class);
       criteria.createAlias("pickListGroupEntity","pickListGroupEntity");
       criteria.add(Restrictions.eq("pickListGroupEntity.id", pickListGroupId));
       return (List<PickListValueEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getPickListGroupPickListValueEntitiesCount(UUID pickListGroupId){
       DetachedCriteria criteria=DetachedCriteria.forClass(PickListValueEntity.class);
       criteria.createAlias("pickListGroupEntity","pickListGroupEntity");
       criteria.add(Restrictions.eq("pickListGroupEntity.id", pickListGroupId));
       return countRows(criteria);
   }
}
