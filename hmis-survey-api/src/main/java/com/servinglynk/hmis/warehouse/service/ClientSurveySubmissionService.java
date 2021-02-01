package com.servinglynk.hmis.warehouse.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface ClientSurveySubmissionService {
	
	UUID createClientSurveySubmission(UUID clientId, UUID surveyId, UUID submissionId, LocalDateTime submissionDate, String surveyCategory);
	
	ClientSurveySubmissions getAllClientSurveySubmissions(UUID clientId,String queryString, String sort, String order, Integer startIndex, Integer maxItems);
	
	void updateClientSurveySubmission(UUID clientSurveySubmissionId, ClientSurveySubmission  clientSurveySubmission, Session session);
	
	ClientSurveySubmissions getSearchClientSurveySubmissions(String queryString, Integer startIndex, Integer maxItems, String sort, String order);

	void updateClientSurveySubmissionDate(UUID surveyId, UUID clientId);
	
	 void indexSurveyData();

}