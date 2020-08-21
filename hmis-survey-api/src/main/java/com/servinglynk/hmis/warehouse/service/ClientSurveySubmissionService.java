package com.servinglynk.hmis.warehouse.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;

public interface ClientSurveySubmissionService {
	
	void createClinetSurveySubmission(UUID clientId, UUID surveyId, UUID submissionId, LocalDateTime submissionDate);
	
	ClientSurveySubmissions getAllClientSurveySubmissions(UUID clientId,String queryString, String sort, String order, Integer startIndex, Integer maxItems);
	
	
	void updateClientSurveySubmission(UUID clientSurveySubmissionId, UUID globalEnrollmentId);
<<<<<<< Updated upstream

=======
	
	void updateHmisPostingStatus(UUID clientSurveySubmissionId, String  hmisPostingStatus);
	
>>>>>>> Stashed changes
	ClientSurveySubmissions getSearchClientSurveySubmissions(String queryString, Integer startIndex, Integer maxItems, String sort, String order);

	void updateClientSurveySubmissionDate(UUID surveyId, UUID clientId);

}