package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.PickListValue;
import com.servinglynk.hmis.warehouse.core.model.PickListValues;
public interface PickListValueService {

   PickListValues createPickListValue(UUID pickListGroupId, PickListValues pickListValues,String caller);
   PickListValue updatePickListValue(PickListValue PickListValue,String caller);
   PickListValue deletePickListValue(UUID PickListValueId,String caller);
   PickListValue getPickListValueById(UUID PickListValueId);
   PickListValues getAllPickListGroupPickListValues(UUID pickListGroupId, Integer startIndex, Integer maxItems);
}
