package com.servinglynk.hmis.warehouse.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.ClientsSurveyScores;
import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
import com.servinglynk.hmis.warehouse.core.model.SectionScore;
import com.servinglynk.hmis.warehouse.core.model.SectionScores;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.TrustedApp;

@RestController
@RequestMapping("/v3/clients")
public class ClientsControllerV3 extends BaseController {

	
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/responses",method=RequestMethod.POST)
	   @APIMapping(value="SURVEY_API_CREATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public Response createResponse(@PathVariable("clientDedupId") UUID clientDedupId,
			   @PathVariable("surveyid")  UUID surveyid,
			   @RequestParam("post-hmis")  String  postHmis,
			   @Valid  @RequestBody Responses response,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
	       return serviceFactory.getResponseServiceV3().createResponse(surveyid,response,client,session.getAccount().getUsername(),postHmis); 
	       
	   }

	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/responses/{responseid}",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public void updateResponse(@PathVariable("clientDedupId") UUID clientDedupId,
			   @PathVariable("surveyid")  UUID surveyid,@PathVariable( "responseid" ) UUID responseId,
			   @Valid  @RequestBody Response response,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
	        response.setResponseId(responseId);
	        response.setSectionId(surveyid);
	        response.setClientId(clientDedupId);
	        response.setDedupClientId(client.getDedupClientId());
	        
	        serviceFactory.getResponseServiceV3().updateResponse(response,session.getAccount().getUsername()); 
	   }
	   
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/submissions/{submissionid}",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public void updateResponsesBySubmission(@PathVariable("clientDedupId") UUID clientDedupId,
			   @PathVariable("surveyid")  UUID surveyid,@PathVariable( "submissionid" ) UUID submissionid,
			   @Valid  @RequestBody Responses response,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
	        serviceFactory.getResponseServiceV3().updateResponsesBySubmissionId(submissionid, response, session.getAccount().getUsername()); 
	   }


	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/responses/{responseid}",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public void deleteResponse(@PathVariable("clientDedupId") UUID clientDedupId,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "responseid" ) UUID responseId,HttpServletRequest request,HttpServletResponse response) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
	        serviceFactory.getSurveyService().getSurveyById(surveyid);
	        serviceFactory.getResponseServiceV3().deleteResponse(responseId,session.getAccount().getUsername()); 
	        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
	   }

	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/submissions/{submissionid}",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public void deleteResponsesBySubmission(@PathVariable("clientDedupId") UUID clientDedupId,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "submissionid" ) UUID submissionid,HttpServletRequest request,HttpServletResponse response) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        serviceFactory.getSurveyService().getSurveyById(surveyid);
	        serviceFactory.getResponseServiceV3().deleteResponsesBySubmissionId(surveyid,submissionid,session.getAccount().getUsername()); 
	        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
	   }
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/responses/{responseid}",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_RESPONSE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Response getResponseById(@PathVariable("clientDedupId") UUID clientDedupId,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "responseid" ) UUID responseId,HttpServletRequest request) throws Exception{
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
		   serviceFactory.getSurveyService().getSurveyById(surveyid);
		   return serviceFactory.getResponseServiceV3().getResponseById(responseId,null); 
	   }

	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/submissions/{submissionid}",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_RESPONSE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Responses getResponseBySubmissionId(@PathVariable("clientDedupId") UUID clientDedupId,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "submissionid" ) UUID submissionid,	               
			   @RequestParam(value="startIndex", required=false,defaultValue="0") Integer startIndex, 
               @RequestParam(value="maxItems", required=false,defaultValue="100") Integer maxItems,
               HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 100) maxItems =100;
		   Session session= sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
		   serviceFactory.getSurveyService().getSurveyById(surveyid);
		   return serviceFactory.getResponseServiceV3().getResponsesBySubmissionId(surveyid,submissionid, startIndex, maxItems); 
	   }

	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/responses",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_ALL_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public Responses getAllSurveyResponses(
			   				@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null || maxItems > 30) maxItems =30;
	           Session session = sessionHelper.getSession(request);
		        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
		        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
	        return serviceFactory.getResponseServiceV3().getAllSurveyResponsesByClientDedupId(surveyid,clientDedupId,startIndex,maxItems,null); 
	   }
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/sections/{sectionid}/scores",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_ALL_SECTION_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public SectionScore getAllSectionScores(@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
	                       HttpServletRequest request) throws Exception {
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
		   SectionScores scores= serviceFactory.getSectionScoreServiceV3().getAllSectionScores(clientDedupId, surveyid, sectionid, 0, 0);
		   if(!scores.getSectionScores().isEmpty())
			   return scores.getSectionScores().get(0);
		   return new SectionScore();
	   }
	   
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/sections/{sectionid}/scores",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_SECTION_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public SectionScores getDeleteSectionScores(@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
           
		   return serviceFactory.getSectionScoreServiceV3().getAllSectionScores(clientDedupId, surveyid, sectionid, startIndex, maxItems);
	   }
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/sections/{sectionid}/scores",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public void getUpdateSectionScores(@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
	                       HttpServletRequest request) throws Exception {
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
		   serviceFactory.getSectionScoreServiceV3().updateSectionScores(client.getClientId(), surveyid, sectionid,session.getAccount().getUsername());
	   }
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/sections/{sectionid}/scores",method=RequestMethod.POST)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public SectionScore createSectionScores(@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
			   				@RequestBody SectionScore sectionScore,
	                       HttpServletRequest request) throws Exception {
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
		   sectionScore.setClientDedupId(clientDedupId);
		   sectionScore.setSectionId(sectionid);
		   sectionScore.setSurveyId(surveyid);
		   sectionScore.setClientId(client.getClientId());
		    serviceFactory.getSectionScoreServiceV3().createSectionScores(sectionScore,session);
		    SectionScore score = new SectionScore();
		    score.setSectionScoreId(sectionScore.getSectionScoreId());
		    score.setSectionScore(sectionScore.getSectionScore());
		    return score;
	   }
	   
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/sections/{sectionid}/scores/{scoreid}",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public void updateSectionScores(@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
			   				@PathVariable("scoreid") UUID scoreid  ,
			   				@RequestBody SectionScore sectionScore,
	                       HttpServletRequest request) throws Exception {
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
		   sectionScore.setClientId(client.getClientId());
		   sectionScore.setClientDedupId(client.getDedupClientId());
		   sectionScore.setSectionId(sectionid);
		   sectionScore.setSurveyId(surveyid);
		   sectionScore.setSectionScoreId(scoreid);
		    serviceFactory.getSectionScoreServiceV3().updateSectionScores(sectionScore,session);
	   }
	   
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/scores",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_ALL_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public SectionScores getAllSurveyScores(@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
		   return serviceFactory.getSectionScoreServiceV3().getAllSectionScores(clientDedupId, surveyid, null, startIndex, maxItems);
	   }
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/scores",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public void getDeleteSurveyScores(@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
		   
           Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
		    serviceFactory.getSectionScoreServiceV3().getAllSectionScores(clientDedupId, surveyid, null, startIndex, maxItems);
	   }
	   
	   @RequestMapping(value="/{clientDedupId}/surveys/{surveyid}/scores",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public void getUpdateSurveyScores(@PathVariable("clientDedupId") UUID clientDedupId,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
		   
           Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        BaseClient client =clientValidator.validateDedupId(clientDedupId, trustedApp, session);
		   serviceFactory.getSectionScoreServiceV3().updateSectionScores(client.getClientId(), surveyid, null,session.getAccount().getUsername());
	   }
	   
	   @RequestMapping(method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public ClientsSurveyScores getAllClientsSurveyScores(@RequestParam(value="startIndex", required=false,defaultValue="0") Integer startIndex, 
               							@RequestParam(value="maxItems", required=false,defaultValue="30") Integer maxItems,
               							HttpServletRequest request) throws Exception {
		   				Session session = sessionHelper.getSession(request);
		   				if(maxItems>50) maxItems=50;
		 return serviceFactory.getSectionScoreServiceV3().calculateClientSurveyScore(startIndex,maxItems,session.getAccount().getProjectGroup().getProjectGroupCode());
	   }
}