package com.hserv.coordinatedentry.housingmatching.model;

public class SurveyScoreModel {
	
	private ClientInfoModel clientInfoModel;
	
	private int surveyScore;

	public ClientInfoModel getClientInfoModel() {
		return clientInfoModel;
	}

	public void setClientInfoModel(ClientInfoModel clientInfoModel) {
		this.clientInfoModel = clientInfoModel;
	}

	public int getSurveyScore() {
		return surveyScore;
	}

	public void setSurveyScore(int surveyScore) {
		this.surveyScore = surveyScore;
	}

	@Override
	public String toString() {
		return "SurveyScoreModel [clientInfoModel=" + clientInfoModel.toString() + ", surveyScore=" + surveyScore + "]";
	}
	
	

}
