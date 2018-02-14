package com.servinglynk.hmis.warehouse.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.util.RestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/clientsurveysubmission")
public class ClientSurveySubmissionController extends BaseController{
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RestClient restClient;
	
	@RequestMapping(value="/clientId/{clientId}",method=RequestMethod.GET)
	@APIMapping(value="SURVEY_API_GET_CLIENT_SURVEY_SUBMISSION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	public List<ClientSurveySubmissionEntity> getAllClientSubmission(@PathVariable UUID clientId, HttpServletRequest request) throws Exception{  
		logger.debug("clientId");
		return serviceFactory.getClientSurveySubmissionService().getClientSurveySubmissionEntitybyClientId(clientId);
	}

	@RequestMapping(value="/id/{id}",method=RequestMethod.PUT)
	@APIMapping(value="SURVEY_API_UPDATE_CLIENT_SURVEY_SUBMISSION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmissionEntity update(@PathVariable UUID id, @RequestBody HashMap<String,String> json, HttpServletRequest request) throws Exception{ 
			
		ClientSurveySubmissionEntity clientSurveySubmissionEntity = 
				serviceFactory.getClientSurveySubmissionService().getClientSurveySubmissionEntitybyId(id);
		clientSurveySubmissionEntity.setGlobalEnrollmentId(UUID.fromString(json.get("global_enrollment_id")));
		logger.debug("getGlobalEnrollmentIdcss {}",clientSurveySubmissionEntity.getGlobalEnrollmentId()); 
		return serviceFactory.getClientSurveySubmissionService().updateClientSurveySubmissionEntity(clientSurveySubmissionEntity);
	}	

	@RequestMapping(value="/client/{clientId}/survey/{surveyId}/submission/{submissionId}",method=RequestMethod.PUT)
	@APIMapping(value="SURVEY_API_UPDATE_SUBMISSION_BY_CLIENT_SURVEY_SUBMISSION",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmissionEntity update( @PathVariable UUID clientId, @PathVariable UUID surveyId,
			@PathVariable UUID submissionId, @RequestBody HashMap<String,String> json, HttpServletRequest request) throws Exception{ 
		
		ClientSurveySubmissionEntity clientSurveySubmissionEntity = serviceFactory.getClientSurveySubmissionService()
				.getClientSurveySubmissionEntitybyClientSurveySubmission(clientId,surveyId,submissionId);
		clientSurveySubmissionEntity.setGlobalEnrollmentId(UUID.fromString(json.get("global_enrollment_id")));
		logger.debug("getGlobalEnrollmentIdcss {}",clientSurveySubmissionEntity.getGlobalEnrollmentId()); 
		return serviceFactory.getClientSurveySubmissionService().updateClientSurveySubmissionEntity(clientSurveySubmissionEntity);
	
	}
	
	
	
}
	

