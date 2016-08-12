package com.hserv.coordinatedentry.housingmatching.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("clientsSurveyScores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientsSurveyScores {

	@JsonProperty("clientsSurveyScores")
	List<ClientSurveyScore> clientsSurveyScores = new ArrayList<ClientSurveyScore>();

	public List<ClientSurveyScore> getClientsSurveyScores() {
		return clientsSurveyScores;
	}

	public void setClientsSurveyScores(List<ClientSurveyScore> clientsSurveyScores) {
		this.clientsSurveyScores = clientsSurveyScores;
	}
	
	public void add(ClientSurveyScore clientSurveyScore){
		this.clientsSurveyScores.add(clientSurveyScore);
	}
}