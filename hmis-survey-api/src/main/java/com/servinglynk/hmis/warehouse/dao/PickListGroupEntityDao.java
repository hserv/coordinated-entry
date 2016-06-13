package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.PickListGroupEntity;

import java.util.List;

public interface PickListGroupEntityDao {

   PickListGroupEntity createPickListGroupEntity(PickListGroupEntity PickListGroupEntity);
   PickListGroupEntity updatePickListGroupEntity(PickListGroupEntity PickListGroupEntity);
   void deletePickListGroupEntity(PickListGroupEntity PickListGroupEntity);
   PickListGroupEntity getPickListGroupEntityById(UUID PickListGroupEntityId);
   List<PickListGroupEntity> getAllPickListGroupEntitys(Integer startIndex, Integer maxItems);
   long getPickListGroupEntitysCount();
}
