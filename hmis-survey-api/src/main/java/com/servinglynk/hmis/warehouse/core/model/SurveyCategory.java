package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveyProject")
public class SurveyCategory extends ClientModel{

      private UUID surveyId;
      
      private String category;
      
	public UUID getSurveyId(){
          return surveyId;
    }
    public void setSurveyId(UUID surveyId){
        this.surveyId = surveyId;
    }
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
    
 }
