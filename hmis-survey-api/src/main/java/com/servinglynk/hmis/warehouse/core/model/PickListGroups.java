package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("pickListGroups")
public class PickListGroups extends PaginatedModel{


       @JsonProperty("pickListGroups") 
       List<PickListGroup> pickListGroups = new ArrayList<PickListGroup>();
       public List<PickListGroup> getpickListGroups() {
           return pickListGroups;
       }

        public void setPickListGroups(List<PickListGroup> pickListGroups) {
           this.pickListGroups = pickListGroups;
       }
 
       public void addPickListGroup(PickListGroup pickListGroup) {
           this.pickListGroups.add(pickListGroup);
       }
 }
