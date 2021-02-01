package com.servinglynk.hmis.warehouse.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.client.MessageSender;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;
import com.servinglynk.hmis.warehouse.core.model.Session;


@RestController
@RequestMapping("/clientsurveysubmissions")
public class ClientSurveySubmissionController extends BaseController{

	
	@RequestMapping(method=RequestMethod.GET,value="/{clientId}")
	@APIMapping(value="SURVEY_API_CREATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmissions getAllClientSurveySubmissions(@PathVariable("clientId") UUID clientId ,
			@RequestParam(value="q",required=false) String queryString,
            @RequestParam(value="sort",defaultValue="submissionDate",required=false) String sort,
            @RequestParam(value="order",defaultValue="asc",required=false) String order,
            @RequestParam(value="startIndex", required=false) Integer startIndex, 
            @RequestParam(value="maxItems", required=false) Integer maxItems,
            HttpServletRequest request) throws Exception {
		 if (startIndex == null) startIndex =0;
         if (maxItems == null || maxItems > 100) maxItems =100;

		return serviceFactory.getClientSurveySubmissionService().getAllClientSurveySubmissions(clientId, queryString,sort,order,startIndex,maxItems);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/{clientSubmissionId}")
	@APIMapping(value="SURVEY_API_CREATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	public void updateGlobalEnrollmentId(@RequestBody ClientSurveySubmission clientSurveySubmission , @PathVariable("clientSubmissionId") UUID clientSubmissionId, HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		serviceFactory.getClientSurveySubmissionService().updateClientSurveySubmission(clientSubmissionId, clientSurveySubmission,session);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@APIMapping(value="SURVEY_API_CREATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmissions getSearchClientSurveySubmissions(@RequestParam(value="q",required=false) String queryString,
            @RequestParam(value="startIndex", required=false) Integer startIndex, 
            @RequestParam(value="maxItems", required=false) Integer maxItems,
            @RequestParam(value="sort",defaultValue="submissionDate",required=false) String sort,
            @RequestParam(value="order",defaultValue="asc",required=false) String order,
            HttpServletRequest request) throws Exception {
		 if (startIndex == null) startIndex =0;
         if (maxItems == null || maxItems > 100) maxItems =100;

		return serviceFactory.getClientSurveySubmissionService().getSearchClientSurveySubmissions(queryString, startIndex,maxItems,sort,order);
	}
	
	
	
	
	@Autowired
	protected MessageSender messageSender;
	
	@RequestMapping(method = RequestMethod.POST)
	@APIMapping(value="HEALTH_CHECK",checkTrustedApp=false,checkSessionToken=false)
	public void indexData() {
		serviceFactory.getClientSurveySubmissionService().indexSurveyData();
	}
	
	
}