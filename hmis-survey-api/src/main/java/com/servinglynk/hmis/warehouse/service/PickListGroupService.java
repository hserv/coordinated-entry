package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.PickListGroup;
import com.servinglynk.hmis.warehouse.core.model.PickListGroups;
public interface PickListGroupService {

   PickListGroup createPickListGroup(PickListGroup PickListGroup,String caller);
   PickListGroup updatePickListGroup(PickListGroup PickListGroup,String caller);
   PickListGroup deletePickListGroup(UUID PickListGroupId,String caller);
   PickListGroup getPickListGroupById(UUID PickListGroupId);
   PickListGroups getAllPickListGroups(Integer startIndex, Integer maxItems);
}
