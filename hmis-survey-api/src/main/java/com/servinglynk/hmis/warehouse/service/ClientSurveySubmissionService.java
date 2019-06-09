package com.servinglynk.hmis.warehouse.service;

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;

public interface ClientSurveySubmissionService {
	
	void createClinetSurveySubmission(UUID clientId, UUID surveyId, UUID submissionId);
	
	ClientSurveySubmissions getAllClientSurveySubmissions(UUID clientId,Integer startIndex, Integer maxItems);
	
	
	void updateClientSurveySubmission(UUID clientSurveySubmissionId, UUID globalEnrollmentId);

	ClientSurveySubmissions getSearchClientSurveySubmissions(String queryString, Integer startIndex, Integer maxItems, String sort, String order);

}