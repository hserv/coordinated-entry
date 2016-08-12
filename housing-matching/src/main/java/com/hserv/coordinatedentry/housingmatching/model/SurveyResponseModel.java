package com.hserv.coordinatedentry.housingmatching.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("clientsSurveyScores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyResponseModel {
	private List<SurveySectionModel> surveySectionList = new ArrayList<>();

	public List<SurveySectionModel> getSurveySectionList() {
		return surveySectionList;
	}

	public void setSurveySectionList(List<SurveySectionModel> surveySectionList) {
		this.surveySectionList = surveySectionList;
	}

}
