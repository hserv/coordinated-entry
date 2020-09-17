package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveyProject")
public class SurveyProject extends ClientModel{

      private UUID surveyProjectId;

      private UUID surveyId;
      
      private String surveyTitle;
      
      private UUID globalProjectId;
      
      private String projectName;
      
      private String schemaVersion;

      
    public UUID getSurveyProjectId() {
		return surveyProjectId;
	}
	public void setSurveyProjectId(UUID surveyProjectId) {
		this.surveyProjectId = surveyProjectId;
	}
	public UUID getGlobalProjectId() {
		return globalProjectId;
	}
	public void setGlobalProjectId(UUID globalProjectId) {
		this.globalProjectId = globalProjectId;
	}
	public String getSchemaVersion() {
		return schemaVersion;
	}
	public void setSchemaVersion(String schemaVersion) {
		this.schemaVersion = schemaVersion;
	}
	public UUID getSurveyId(){
          return surveyId;
    }
    public void setSurveyId(UUID surveyId){
        this.surveyId = surveyId;
    }
	public String getSurveyTitle() {
		return surveyTitle;
	}
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
 }
