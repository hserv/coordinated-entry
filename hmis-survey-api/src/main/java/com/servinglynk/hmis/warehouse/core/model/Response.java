package com.servinglynk.hmis.warehouse.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonRootName("response")
public class Response extends ClientModel {

	private UUID responseId;

	private String responseText;

	private int questionScore;

	private String appId;

	private UUID sectionId;

	private UUID questionId;

	private UUID clientId;
	
	private UUID dedupClientId;
	
	private String clientLink;
	
	private String hmisLink;

	private UUID surveyId;

	private boolean refused;

	private UUID submissionId;
	
	private Client client;
	
	@JsonDeserialize(using=JsonTimestampDeserializer.class)
	@JsonSerialize(using=JsonTimestampSerializer.class)
	private LocalDateTime effectiveDate;

	public Response() {
		super();
	}

	public Response(UUID responseId) {
		super();
		this.responseId = responseId;
	}

	public UUID getResponseId() {
		return responseId;
	}

	public void setResponseId(UUID responseId) {
		this.responseId = responseId;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public int getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(int questionScore) {
		this.questionScore = questionScore;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public UUID getSectionId() {
		return sectionId;
	}

	public void setSectionId(UUID sectionId) {
		this.sectionId = sectionId;
	}

	public UUID getQuestionId() {
		return questionId;
	}

	public void setQuestionId(UUID questionId) {
		this.questionId = questionId;
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

	public boolean isRefused() {
		return refused;
	}

	public void setRefused(boolean refused) {
		this.refused = refused;
	}

	public UUID getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(UUID submissionId) {
		this.submissionId = submissionId;
	}

	public String getClientLink() {
		return clientLink;
	}

	public void setClientLink(String clientLink) {
		this.clientLink = clientLink;
	}

	public LocalDateTime getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public UUID getDedupClientId() {
		return dedupClientId;
	}

	public void setDedupClientId(UUID dedupClientId) {
		this.dedupClientId = dedupClientId;
	}

	public String getHmisLink() {
		return hmisLink;
	}

	public void setHmisLink(String hmisLink) {
		this.hmisLink = hmisLink;
	}
}