package com.hserv.coordinatedentry.housingmatching.model;

import java.util.UUID;

public class ClientSurveyScore {

	private UUID surveyId;
	private UUID clientId;
	private Long surveyScore;
	public UUID getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(UUID surveyId) {
		this.surveyId = surveyId;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public Long getSurveyScore() {
		return surveyScore;
	}
	public void setSurveyScore(Long surveyScore) {
		this.surveyScore = surveyScore;
	}
}