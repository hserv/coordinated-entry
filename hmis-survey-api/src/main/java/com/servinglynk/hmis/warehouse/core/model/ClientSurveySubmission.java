package com.servinglynk.hmis.warehouse.core.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.servinglynk.hmis.warehouse.core.model.ClientModel;

@JsonRootName("clientsurveysubmission")
public class ClientSurveySubmission extends ClientModel {


	private UUID id;
	private UUID clientId;
	private UUID surveyId; 
	private UUID submissionId;
   	private UUID globalEnrollmentId;

   	private Client client;

	private String clientLink;
   	
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
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getClientLink() {
		return clientLink;
	}
	public void setClientLink(String clientLink) {
		this.clientLink = clientLink;
	}  	
}