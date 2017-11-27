package com.servinglynk.hmis.warehouse.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.TrustedApp;

@RestController
@RequestMapping("/v2/clients")
public class ClientsControllerV2  extends BaseController {
	
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/responses/{responseid}",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_RESPONSE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Response getResponseById(@PathVariable("clientid") UUID clientid,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "responseid" ) UUID responseId,HttpServletRequest request) throws Exception{
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateClient(clientid, trustedApp, session);
		   serviceFactory.getSurveyService().getSurveyById(surveyid);
		   return serviceFactory.getResponseService().getResponseById(responseId,"v2"); 
	   }
	   
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/responses",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_ALL_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public Responses getAllSurveyResponsesv2(
			   				@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null || maxItems > 30) maxItems =30;
	           Session session = sessionHelper.getSession(request);
		        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
		        BaseClient client =clientValidator.validateClient(clientid, trustedApp, session);
	        return serviceFactory.getResponseService().getAllSurveyResponses(surveyid,startIndex,maxItems,"v2"); 
	   }

}
