package com.hserv.coordinatedentry.housingmatching.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveySectionModel {

	@JsonProperty(value = "section_score")
	private Integer sectionScore;

	@JsonProperty(value = "client_id")
	private String clientId;

	public Integer getSectionScore() {
		return sectionScore;
	}

	public void setSectionScore(Integer sectionScore) {
		this.sectionScore = sectionScore;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
