package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.PickListGroupEntity;

@Component
public class PickListGroupEntityDaoImpl extends QueryExecutorImpl implements PickListGroupEntityDao {

   public PickListGroupEntity createPickListGroupEntity(PickListGroupEntity PickListGroupEntity){
       PickListGroupEntity.setId(UUID.randomUUID()); 
       insert(PickListGroupEntity);
       return PickListGroupEntity;
   }
   public PickListGroupEntity updatePickListGroupEntity(PickListGroupEntity PickListGroupEntity){
       update(PickListGroupEntity);
       return PickListGroupEntity;
   }
   public void deletePickListGroupEntity(PickListGroupEntity PickListGroupEntity){
       delete(PickListGroupEntity);
   }
   public PickListGroupEntity getPickListGroupEntityById(UUID PickListGroupEntityId){ 
       return (PickListGroupEntity) get(PickListGroupEntity.class, PickListGroupEntityId);
   }
   @SuppressWarnings("unchecked")
   public List<PickListGroupEntity> getAllPickListGroupEntitys(Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(PickListGroupEntity.class);
       return (List<PickListGroupEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getPickListGroupEntitysCount(){
       DetachedCriteria criteria=DetachedCriteria.forClass(PickListGroupEntity.class);
       return countRows(criteria);
   }
}
