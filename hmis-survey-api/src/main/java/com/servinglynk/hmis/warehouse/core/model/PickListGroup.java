package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("pickListGroup")
public class PickListGroup extends ClientModel{


      private UUID pickListGroupId;

      @NotBlank(message="PickList Group Name is required")
      @NotEmpty(message="PickList Group Name is required")
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
