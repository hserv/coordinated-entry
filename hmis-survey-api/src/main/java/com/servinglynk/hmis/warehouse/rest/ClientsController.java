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
import com.servinglynk.hmis.warehouse.core.model.ClientsSurveyScores;
import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
import com.servinglynk.hmis.warehouse.core.model.SectionScore;
import com.servinglynk.hmis.warehouse.core.model.SectionScores;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.TrustedApp;

@RestController
@RequestMapping("/clients")
public class ClientsController extends BaseController {

	
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/responses",method=RequestMethod.POST)
	   @APIMapping(value="SURVEY_API_CREATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public Response createResponse(@PathVariable("clientid") UUID clientid,
			   @PathVariable("surveyid")  UUID surveyid,
			   @Valid  @RequestBody Responses response,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
	       return serviceFactory.getResponseService().createResponse(clientid,surveyid,response,clientLink,session.getAccount().getUsername()); 
	       
	   }

	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/responses/{responseid}",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public void updateResponse(@PathVariable("clientid") UUID clientid,
			   @PathVariable("surveyid")  UUID surveyid,@PathVariable( "responseid" ) UUID responseId,
			   @Valid  @RequestBody Response response,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
	        response.setResponseId(responseId);
	        response.setSectionId(surveyid);
	        response.setClientId(clientid);
	        
	        serviceFactory.getResponseService().updateResponse(response,session.getAccount().getUsername()); 
	   }
	   
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/submissions/{submissionid}",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public void updateResponsesBySubmission(@PathVariable("clientid") UUID clientid,
			   @PathVariable("surveyid")  UUID surveyid,@PathVariable( "submissionid" ) UUID submissionid,
			   @Valid  @RequestBody Responses response,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
	        serviceFactory.getResponseService().updateResponsesBySubmissionId(submissionid, response, session.getAccount().getUsername()); 
	   }


	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/responses/{responseid}",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public void deleteResponse(@PathVariable("clientid") UUID clientid,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "responseid" ) UUID responseId,HttpServletRequest request,HttpServletResponse response) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
	        serviceFactory.getSurveyService().getSurveyById(surveyid);
	        serviceFactory.getResponseService().deleteResponse(responseId,session.getAccount().getUsername()); 
	        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
	   }

	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/submissions/{submissionid}",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public void deleteResponsesBySubmission(@PathVariable("clientid") UUID clientid,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "submissionid" ) UUID submissionid,HttpServletRequest request,HttpServletResponse response) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
	        serviceFactory.getSurveyService().getSurveyById(surveyid);
	        serviceFactory.getResponseService().deleteResponsesBySubmissionId(surveyid,submissionid,session.getAccount().getUsername()); 
	        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
	   }
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/responses/{responseid}",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_RESPONSE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Response getResponseById(@PathVariable("clientid") UUID clientid,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "responseid" ) UUID responseId,HttpServletRequest request) throws Exception{
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		   serviceFactory.getSurveyService().getSurveyById(surveyid);
		   return serviceFactory.getResponseService().getResponseById(responseId); 
	   }

	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/submissions/{submissionid}",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_RESPONSE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Responses getResponseBySubmissionId(@PathVariable("clientid") UUID clientid,
			   @PathVariable("surveyid")  UUID surveyid,
			   @PathVariable( "submissionid" ) UUID submissionid,	               
			   @RequestParam(value="startIndex", required=false,defaultValue="0") Integer startIndex, 
               @RequestParam(value="maxItems", required=false,defaultValue="30") Integer maxItems,
               HttpServletRequest request) throws Exception {
		   Session session= sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		   serviceFactory.getSurveyService().getSurveyById(surveyid);
		   return serviceFactory.getResponseService().getResponsesBySubmissionId(surveyid,submissionid, startIndex, maxItems); 
	   }

	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/responses",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_ALL_RESPONSE",checkTrustedApp=true,checkSessionToken=true)
	   public Responses getAllSurveyResponses(
			   				@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null || maxItems > 30) maxItems =30;
	           Session session = sessionHelper.getSession(request);
		        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
		        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
	        return serviceFactory.getResponseService().getAllSurveyResponses(surveyid,startIndex,maxItems); 
	   }
	   
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/sections/{sectionid}/scores",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_ALL_SECTION_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public SectionScore getAllSectionScores(@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
	                       HttpServletRequest request) throws Exception {
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		   SectionScores scores= serviceFactory.getSectionScoreService().getAllSectionScores(clientid, surveyid, sectionid, 0, 0);
		   if(!scores.getSectionScores().isEmpty())
			   return scores.getSectionScores().get(0);
		   return new SectionScore();
	   }
	   
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/sections/{sectionid}/scores",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_SECTION_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public SectionScores getDeleteSectionScores(@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
           
		   return serviceFactory.getSectionScoreService().getAllSectionScores(clientid, surveyid, sectionid, startIndex, maxItems);
	   }
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/sections/{sectionid}/scores",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public void getUpdateSectionScores(@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
	                       HttpServletRequest request) throws Exception {
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		   serviceFactory.getSectionScoreService().updateSectionScores(clientid, surveyid, sectionid,session.getAccount().getUsername());
	   }
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/sections/{sectionid}/scores",method=RequestMethod.POST)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public SectionScore createSectionScores(@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
			   				@RequestBody SectionScore sectionScore,
	                       HttpServletRequest request) throws Exception {
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		   sectionScore.setClientId(clientid);
		   sectionScore.setSectionId(sectionid);
		   sectionScore.setSurveyId(surveyid);
		    serviceFactory.getSectionScoreService().createSectionScores(sectionScore,session);
		    SectionScore score = new SectionScore();
		    score.setSectionScoreId(sectionScore.getSectionScoreId());
		    score.setSectionScore(sectionScore.getSectionScore());
		    return score;
	   }
	   
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/sections/{sectionid}/scores/{scoreid}",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public void updateSectionScores(@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
			   				@PathVariable("sectionid")  UUID sectionid,
			   				@PathVariable("scoreid") UUID scoreid  ,
			   				@RequestBody SectionScore sectionScore,
	                       HttpServletRequest request) throws Exception {
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		   sectionScore.setClientId(clientid);
		   sectionScore.setSectionId(sectionid);
		   sectionScore.setSurveyId(surveyid);
		   sectionScore.setSectionScoreId(scoreid);
		    serviceFactory.getSectionScoreService().updateSectionScores(sectionScore,session);
	   }
	   
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/scores",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_ALL_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public SectionScores getAllSurveyScores(@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
		   Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		   return serviceFactory.getSectionScoreService().getAllSectionScores(clientid, surveyid, null, startIndex, maxItems);
	   }
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/scores",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public void getDeleteSurveyScores(@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
		   
           Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		    serviceFactory.getSectionScoreService().getAllSectionScores(clientid, surveyid, null, startIndex, maxItems);
	   }
	   
	   @RequestMapping(value="/{clientid}/surveys/{surveyid}/scores",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public void getUpdateSurveyScores(@PathVariable("clientid") UUID clientid,
			   				@PathVariable("surveyid")  UUID surveyid,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
		   
           Session session = sessionHelper.getSession(request);
	        TrustedApp trustedApp = trustedAppHelper.getTrustedApp(request);
	        String clientLink =clientValidator.validateClient(clientid, trustedApp, session);
		   serviceFactory.getSectionScoreService().updateSectionScores(clientid, surveyid, null,session.getAccount().getUsername());
	   }
	   
	   @RequestMapping(method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_UPDATE_SURVEY_SCORES",checkTrustedApp=true,checkSessionToken=true)	   
	   public ClientsSurveyScores getAllClientsSurveyScores(@RequestParam(value="startIndex", required=false,defaultValue="0") Integer startIndex, 
               							@RequestParam(value="maxItems", required=false,defaultValue="30") Integer maxItems,
               							HttpServletRequest request) throws Exception {
		   				Session session = sessionHelper.getSession(request);
		   				if(maxItems>50) maxItems=50;
		 return serviceFactory.getSectionScoreService().calculateClientSurveyScore(startIndex,maxItems,session.getAccount().getProjectGroup().getProjectGroupCode());
	   }
}