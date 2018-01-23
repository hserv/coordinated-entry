package com.servinglynk.hmis.warehouse.core.model;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value = "ClientSurveySubmission")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientSurveySubmission {
	
	
	private UUID id;
	private UUID clientId;
	private UUID surveyId; 
	private UUID submissionId;
    private UUID globalEnrollmentId;
	
    public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public UUID getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(UUID surveyId) {
		this.surveyId = surveyId;
	}
	public UUID getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(UUID submissionId) {
		this.submissionId = submissionId;
	}
	public UUID getGlobalEnrollmentId() {
		return globalEnrollmentId;
	}
	public void setGlobalEnrollmentId(UUID globalEnrollmentId) {
		this.globalEnrollmentId = globalEnrollmentId;
	}

    
    
}
