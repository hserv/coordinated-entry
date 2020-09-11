package com.servinglynk.hmis.warehouse.core.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.servinglynk.hmis.warehouse.core.annotations.AllowedValues;

@JsonRootName("clientsurveysubmission")
public class ClientSurveySubmission extends ClientModel {


	private UUID id;
	private UUID clientId;
	private UUID surveyId; 
	private Survey survey;
	private UUID submissionId;
   	private UUID globalEnrollmentId;
	private UUID globalProjectId;
   	@AllowedValues( allowNullDefault = true, values = { "INITIAL", "SUBMITTED", "UPDATED"}, message = "Allowed values for tag value are INITIAL,SUBMITTED,RESUBMITTED")
   	private String hmisPostingStatus;
   	private String surveyCategory;
   	
	@JsonDeserialize(using=JsonTimestampDeserializer.class)
	@JsonSerialize(using=JsonTimestampSerializer.class)
   	private LocalDateTime submissionDate;
	
   	private Date entryDate;
	
   	private Date exitDate;
	
   	private Date informationDate;

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
	public UUID getGlobalProjectId() {
		return globalProjectId;
	}
	public void setGlobalProjectId(UUID globalProjectId) {
		this.globalProjectId = globalProjectId;
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
	public LocalDateTime getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(LocalDateTime submissionDate) {
		this.submissionDate = submissionDate;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	public Date getInformationDate() {
		return informationDate;
	}
	public void setInformationDate(Date informationDate) {
		this.informationDate = informationDate;
	}
	public String getHmisPostingStatus() {
		return hmisPostingStatus;
	}
	public void setHmisPostingStatus(String hmisPostingStatus) {
		this.hmisPostingStatus = hmisPostingStatus;
	}
	public String getSurveyCategory() {
		return surveyCategory;
	}
	public void setSurveyCategory(String surveyCategory) {
		this.surveyCategory = surveyCategory;
	}  	
}