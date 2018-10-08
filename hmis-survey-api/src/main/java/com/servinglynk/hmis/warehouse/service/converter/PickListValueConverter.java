package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.PickListValue;
import com.servinglynk.hmis.warehouse.model.PickListValueEntity;
public class PickListValueConverter  {

   public static PickListValueEntity modelToEntity (PickListValue model ,PickListValueEntity entity) {
       if(entity==null) entity = new PickListValueEntity();
       entity.setId(model.getPickListValueId());
       entity.setPickListValueCode(model.getPickListValueCode());
       entity.setValueText(model.getValueText());
//       entity.setPickListGroupId(model.getPickListGroupId());
       if(model.getSortOrder()!=null) entity.setSortOrder(model.getSortOrder());
       return entity;    
   }


   public static PickListValue entityToModel (PickListValueEntity entity) {
       PickListValue model = new PickListValue();
       model.setPickListValueId(entity.getId());
       model.setPickListValueCode(entity.getPickListValueCode());
       model.setValueText(entity.getValueText());
       if(entity.getSortOrder()!=null) model.setSortOrder(entity.getSortOrder());
//       model.setPickListGroupId(entity.getPickListGroupId());
       return model;
   }


}
