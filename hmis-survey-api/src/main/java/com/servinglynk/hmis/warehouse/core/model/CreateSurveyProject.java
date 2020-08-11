package com.servinglynk.hmis.warehouse.core.model; 

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveyProject")
public class CreateSurveyProject extends ClientModel{


      private UUID surveyProjectId;

      private UUID surveyId;
      
      private Set<GlobalProject> globalProjects;
      
      private String schemaVersion;

      
    public UUID getSurveyProjectId() {
		return surveyProjectId;
	}
	public void setSurveyProjectId(UUID surveyProjectId) {
		this.surveyProjectId = surveyProjectId;
	}
	public Set<GlobalProject> getGlobalProjects() {
		return globalProjects;
	}
	public void setGlobalProjects(Set<GlobalProject> globalProjects) {
		this.globalProjects = globalProjects;
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
 }
