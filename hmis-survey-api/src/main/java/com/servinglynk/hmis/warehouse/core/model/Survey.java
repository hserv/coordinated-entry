package com.servinglynk.hmis.warehouse.core.model;

import java.util.Set;
import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.servinglynk.hmis.warehouse.core.annotations.AllowedValues;

@JsonRootName("survey")
public class Survey extends ClientModel {

	private UUID surveyId;

	@NotBlank(message = "Survey Title is required")
	@NotEmpty(message = "Survey Title is required")
	private String surveyTitle;

	@NotBlank(message = "Survey Owner is required")
	@NotEmpty(message = "Survey Owner is required")
	private String surveyOwner;

	@AllowedValues(values = { "SINGLE_ADULT", "FAMILY", "YOUTH",
			"HUD" }, message = "Allowed values for tag value are SINGLE_ADULT,FAMILY,YOUTH,HUD")
	private String tagValue;

	private String projectGroupCode;

	private boolean locked;

	private boolean copySurveyId;
	
	private String hmisVersion;
	
	private Set<String> surveyCategories;


	public UUID getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(UUID surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyTitle() {
		return surveyTitle;
	}

	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

	public String getSurveyOwner() {
		return surveyOwner;
	}

	public void setSurveyOwner(String surveyOwner) {
		this.surveyOwner = surveyOwner;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public String getProjectGroupCode() {
		return projectGroupCode;
	}

	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
	}

	public boolean getCopySurveyId() {
		return copySurveyId;
	}

	public void setCopySurveyId(boolean copySurveyId) {
		this.copySurveyId = copySurveyId;
	}

	public boolean getLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getHmisVersion() {
		return hmisVersion;
	}

	public void setHmisVersion(String hmisVersion) {
		this.hmisVersion = hmisVersion;
	}

	public Set<String> getSurveyCategories() {
		return surveyCategories;
	}

	public void setSurveyCategories(Set<String> surveyCategories) {
		this.surveyCategories = surveyCategories;
	}
}