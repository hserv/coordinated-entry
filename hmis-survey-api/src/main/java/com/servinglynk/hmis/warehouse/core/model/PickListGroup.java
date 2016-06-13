package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("pickListGroup")
public class PickListGroup extends ClientModel{


      private UUID pickListGroupId;

      private String pickListGroupName;



      public UUID getPickListGroupId(){
          return pickListGroupId;
      }
      public void setPickListGroupId(UUID pickListGroupId){
          this.pickListGroupId = pickListGroupId;
      }
      public String getPickListGroupName(){
          return pickListGroupName;
      }
      public void setPickListGroupName(String pickListGroupName){
          this.pickListGroupName = pickListGroupName;
      }

 }
