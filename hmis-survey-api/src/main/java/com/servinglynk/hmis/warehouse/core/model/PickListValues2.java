package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("pickList")
public class PickListValues2 {


       @JsonProperty("pickList") 
       List<PickListValue> pickListValues = new ArrayList<PickListValue>();
       public List<PickListValue> getPickListValues() {
           return pickListValues;
       }

        public void setPickListValues(List<PickListValue> pickListValues) {
           this.pickListValues = pickListValues;
       }
 
       public void addPickListValue(PickListValue pickListValue) {
           this.pickListValues.add(pickListValue);
       }
 }
