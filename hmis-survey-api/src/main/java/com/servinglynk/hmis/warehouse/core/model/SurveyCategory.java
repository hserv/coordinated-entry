package com.servinglynk.hmis.warehouse.core.model; 

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveyCategory")
public class SurveyCategory extends ClientModel{

	private String category;
      
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
    
 }
