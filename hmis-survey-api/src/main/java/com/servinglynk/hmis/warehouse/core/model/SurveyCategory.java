package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveyCategory")
public class SurveyCategory extends ClientModel{

	private String category;
	private UUID surveyId;
	private UUID surveyCategoryId;
      
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public UUID getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(UUID surveyId) {
		this.surveyId = surveyId;
	}
	public UUID getSurveyCategoryId() {
		return surveyCategoryId;
	}
	public void setSurveyCategoryId(UUID surveyCategoryId) {
		this.surveyCategoryId = surveyCategoryId;
	}
 }
