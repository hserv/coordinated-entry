package com.servinglynk.hmis.warehouse.core.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("clientsSurveyScores")
public class ClientsSurveyScores {

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