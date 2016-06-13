package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.PickListGroup;
import com.servinglynk.hmis.warehouse.model.PickListGroupEntity;
public class PickListGroupConverter{

   public static PickListGroupEntity modelToEntity (PickListGroup model ,PickListGroupEntity entity) {
       if(entity==null) entity = new PickListGroupEntity();
       entity.setId(model.getPickListGroupId());
       entity.setPickListGroupName(model.getPickListGroupName());
       return entity;    
   }


   public static PickListGroup entityToModel (PickListGroupEntity entity) {
       PickListGroup model = new PickListGroup();
       model.setPickListGroupId(entity.getId());
       model.setPickListGroupName(entity.getPickListGroupName());
       return model;
   }


}
