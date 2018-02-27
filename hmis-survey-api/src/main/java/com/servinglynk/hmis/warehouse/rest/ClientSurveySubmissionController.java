package com.servinglynk.hmis.warehouse.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;

@RestController
@RequestMapping("/clientsurveysubmissions")
public class ClientSurveySubmissionController extends BaseController{

	
	@RequestMapping(method=RequestMethod.GET,value="/{clientId}")
	@APIMapping(value="SURVEY_API_CREATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmissions getAllClientSurveySubmissions(@PathVariable("clientId") UUID clientId ,
            @RequestParam(value="startIndex", required=false) Integer startIndex, 
            @RequestParam(value="maxItems", required=false) Integer maxItems,
            HttpServletRequest request) throws Exception {
		 if (startIndex == null) startIndex =0;
         if (maxItems == null || maxItems > 30) maxItems =30;

		return serviceFactory.getClientSurveySubmissionService().getAllClientSurveySubmissions(clientId, startIndex,maxItems);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/{clientSubmissionId}")
	@APIMapping(value="SURVEY_API_CREATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	public void updateGlobalEnrollmentId(@RequestBody ClientSurveySubmission clientSurveySubmission , @PathVariable("clientSubmissionId") UUID clientSubmissionId ) throws Exception {
		serviceFactory.getClientSurveySubmissionService().updateClientSurveySubmission(clientSubmissionId, clientSurveySubmission.getGlobalEnrollmentId());
	}
	
	
}