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
import com.servinglynk.hmis.warehouse.core.model.SectionQuestionMappings;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.Survey;
import com.servinglynk.hmis.warehouse.core.model.SurveySection;
import com.servinglynk.hmis.warehouse.core.model.SurveySections;
import com.servinglynk.hmis.warehouse.core.model.Surveys;

@RestController
@RequestMapping("/surveys")
public class SurveysController extends BaseController { 

   @RequestMapping(method=RequestMethod.POST)
   @APIMapping(value="SURVEY_API_CREATE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public Survey createSurvey(@Valid @RequestBody Survey survey,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
         serviceFactory.getSurveyService().createSurvey(survey,session); 
         Survey returnsurvey = new Survey();
         returnsurvey.setSurveyId(survey.getSurveyId());
         return returnsurvey;
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public void updateSurvey(@PathVariable( "surveyid" ) UUID surveyId,@Valid @RequestBody Survey survey,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        survey.setSurveyId(surveyId);
        serviceFactory.getSurveyService().updateSurvey(survey,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public void deleteSurvey(@PathVariable( "surveyid" ) UUID surveyId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getSurveyService().deleteSurvey(surveyId,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_SURVEY_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public Survey getSurveyById(@PathVariable( "surveyid" ) UUID surveyId,HttpServletRequest request) throws Exception{
        return serviceFactory.getSurveyService().getSurveyById(surveyId); 
   }

   @RequestMapping(method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_ALL_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public Surveys getAllSurveys(
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
           Session session = sessionHelper.getSession(request);
        return serviceFactory.getSurveyService().getAllSurveys(startIndex,maxItems,session.getAccount().getProjectGroup().getProjectGroupCode()); 
   }

   
   
   @RequestMapping(method=RequestMethod.POST,value="/{surveyid}/surveysections")
   @APIMapping(value="SURVEY_API_CREATE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public SurveySection createSurveySection(@PathVariable("surveyid") UUID surveyid,
		   @Valid @RequestBody SurveySection surveySection,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
         serviceFactory.getSurveySectionService().createSurveySection(surveyid,surveySection,session.getAccount().getUsername()); 
         SurveySection returnSurveySection = new SurveySection();
         returnSurveySection.setSurveySectionId(surveySection.getSurveySectionId());
         return returnSurveySection;
   }

   @RequestMapping(value="/{surveyid}/surveysections/{surveysectionid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public void updateSurveySection(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveysectionid" ) UUID SurveySectionId,@Valid @RequestBody SurveySection surveySection,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        surveySection.setSurveySectionId(SurveySectionId);
        surveySection.setSurveyId(surveyid);
        serviceFactory.getSurveySectionService().updateSurveySection(surveyid,surveySection,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{surveyid}/surveysections/{surveysectionid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public void deleteSurveySection(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveysectionid" ) UUID SurveySectionId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getSurveyService().getSurveyById(surveyid);
        serviceFactory.getSurveySectionService().deleteSurveySection(SurveySectionId,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{surveyid}/surveysections/{surveysectionid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_SURVEYSECTION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public SurveySection getSurveySectionById(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveysectionid" ) UUID SurveySectionId,HttpServletRequest request) throws Exception{
       serviceFactory.getSurveyService().getSurveyById(surveyid);
	   return serviceFactory.getSurveySectionService().getSurveySectionById(SurveySectionId); 
   }

   @RequestMapping(method=RequestMethod.GET,value="/{surveyid}/surveysections")
   @APIMapping(value="SURVEY_API_GET_ALL_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public SurveySections getAllSurveySections(@PathVariable("surveyid") UUID surveyid,
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
           serviceFactory.getSurveyService().getSurveyById(surveyid);
        return serviceFactory.getSurveySectionService().getAllSurveySurveySections(surveyid,startIndex,maxItems); 
   }
   
   
   @RequestMapping(value="/{surveyid}/surveysections/{surveysectionid}/questions",method=RequestMethod.POST)
   @APIMapping(value="SURVEY_API_CREATE_SECTION_QUESTIONMAPPING",checkTrustedApp=true,checkSessionToken=true)
   public void createSectionQuestionMapping(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable("surveysectionid") UUID surveysectionid,
		   @RequestBody SectionQuestionMappings questions,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getSurveyService().getSurveyById(surveyid);
        serviceFactory.getSectionQuestionMappingService().createSectionQuestionMapping(surveysectionid,questions, session.getAccount().getUsername());
   }

   
   @RequestMapping(value="/{surveyid}/surveysections/{surveysectionid}/questions",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_CREATE_SECTIONQUESTIONMAPPING",checkTrustedApp=true,checkSessionToken=true)
   public SectionQuestionMappings getSectionQuestions(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable("surveysectionid") UUID surveysectionid,
           @RequestParam(value="startIndex", required=false) Integer startIndex, 
           @RequestParam(value="maxItems", required=false) Integer maxItems,
           HttpServletRequest request) throws Exception{
        serviceFactory.getSurveyService().getSurveyById(surveyid);
        
        if (startIndex == null) startIndex =0;
        if (maxItems == null || maxItems > 30) maxItems =30;
       return serviceFactory.getSectionQuestionMappingService().getAllSectionQuestionMappings(surveysectionid,startIndex, maxItems);   
   }
      
   @RequestMapping(value="/{surveyid}/surveysections/{surveysectionid}/questions/{questionid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_CREATE_SECTIONQUESTIONMAPPING",checkTrustedApp=true,checkSessionToken=true)
   public void deleteSectionQuestionById(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable("surveysectionid") UUID surveysectionid,
		   @PathVariable("questionid") UUID questionid,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getSurveyService().getSurveyById(surveyid);
        serviceFactory.getSurveySectionService().getSurveySectionById(surveysectionid);
        serviceFactory.getSectionQuestionMappingService().deleteSectionQuestionMapping(questionid, session.getAccount().getUsername());
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

}