package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.PickListValueEntity;

import java.util.List;

public interface PickListValueEntityDao {

   PickListValueEntity createPickListValueEntity(PickListValueEntity PickListValueEntity);
   PickListValueEntity updatePickListValueEntity(PickListValueEntity PickListValueEntity);
   void deletePickListValueEntity(PickListValueEntity PickListValueEntity);
   PickListValueEntity getPickListValueEntityById(UUID PickListValueEntityId);
   List<PickListValueEntity> getAllPickListGroupPickListValueEntities(UUID pickListGroupId, Integer startIndex, Integer maxItems);
   long getPickListGroupPickListValueEntitiesCount(UUID pickListGroupId);
}
