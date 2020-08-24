package com.servinglynk.hmis.warehouse.rest; 

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.CreateSurveyProject;
import com.servinglynk.hmis.warehouse.core.model.ParamResponse;
import com.servinglynk.hmis.warehouse.core.model.SectionQuestionMappings;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.Survey;
import com.servinglynk.hmis.warehouse.core.model.SurveyCategories;
import com.servinglynk.hmis.warehouse.core.model.SurveyCategory;
import com.servinglynk.hmis.warehouse.core.model.SurveyProject;
import com.servinglynk.hmis.warehouse.core.model.SurveyProjects;
import com.servinglynk.hmis.warehouse.core.model.SurveySection;
import com.servinglynk.hmis.warehouse.core.model.SurveySections;
import com.servinglynk.hmis.warehouse.core.model.Surveys;
import com.servinglynk.hmis.warehouse.enums.HmisVersionEnum;
import com.servinglynk.hmis.warehouse.enums.SurveyCategoryEnum;

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
   /*** Survey global project relation  Begins ***/
   @RequestMapping(method=RequestMethod.POST,value="/{surveyid}/projects")
   @APIMapping(value="SURVEY_API_CREATE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public SurveyProject createSurveyProject(@PathVariable("surveyid") UUID surveyid,
		   @Valid @RequestBody CreateSurveyProject surveyProject,HttpServletRequest request) throws Exception{
         Session session = sessionHelper.getSession(request); 
         serviceFactory.getSurveyProjectService().createSurveyProject(surveyid,surveyProject,session.getAccount().getUsername()); 
         SurveyProject returnedSurveyProject = new SurveyProject();
         returnedSurveyProject.setSurveyProjectId(surveyProject.getSurveyProjectId());
         return returnedSurveyProject;
   }

   @RequestMapping(value="/{surveyid}/projects/{surveyprojectid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public void updateSurveyProject(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveyprojectid" ) UUID projectid,@Valid @RequestBody SurveyProject surveyProject,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        surveyProject.setSurveyProjectId(projectid);
        surveyProject.setSurveyId(surveyid);
        serviceFactory.getSurveyProjectService().updateSurveyProject(surveyid,surveyProject,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{surveyid}/projects/{surveyprojectid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public void deleteSurveyProjects(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveyprojectid" ) UUID projectid,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getSurveyService().getSurveyById(surveyid);
        serviceFactory.getSurveyProjectService().deleteSurveyProject(projectid,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{surveyid}/projects/{surveyprojectid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_SURVEYSECTION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public SurveyProject getSurveyProjectById(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveyprojectid" ) UUID projectid,HttpServletRequest request) throws Exception{
       serviceFactory.getSurveyService().getSurveyById(surveyid);
	   return serviceFactory.getSurveyProjectService().getSurveyProjectById(projectid); 
   }

   @RequestMapping(method=RequestMethod.GET,value="/{surveyid}/projects")
   @APIMapping(value="SURVEY_API_GET_ALL_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public SurveyProjects getAllSurveyProjets(@PathVariable("surveyid") UUID surveyid,
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       @RequestParam(value="globalProjectId", required=false) UUID globalProjectId,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
           serviceFactory.getSurveyService().getSurveyById(surveyid);
           if(globalProjectId != null) {
        	  return serviceFactory.getSurveyProjectService().getAllSurveyByGlobaProjectId(globalProjectId, startIndex, maxItems);
           }
        return serviceFactory.getSurveyProjectService().getAllSurveySurveyProjects(surveyid,startIndex,maxItems); 
   }
   
   @RequestMapping(method=RequestMethod.POST,value="/{surveyid}/categories")
   @APIMapping(value="SURVEY_API_CREATE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public SurveyCategories createSurveyCategory(@PathVariable("surveyid") UUID surveyid,
		   @Valid @RequestBody SurveyCategories surveyCategories,HttpServletRequest request) throws Exception{
         serviceFactory.getSurveyCategoryService().createSurveyCategory(surveyid, surveyCategories.getSurveyCategories());
         return surveyCategories;
   }
   
   @RequestMapping(value="/{surveyid}/categories/{surveycategoryid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public void deleteSurveyCategories(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveycategoryid" ) UUID surveycategoryid,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getSurveyService().getSurveyById(surveyid);
        serviceFactory.getSurveyCategoryService().deleteSurveyCategory(surveycategoryid,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }
   
   @RequestMapping(value="/{surveyid}/categories/{surveycategoryid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public void updateSurveyCategory(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveycategoryid" ) UUID surveycategoryid,@Valid @RequestBody SurveyCategory surveyCategory,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        surveyCategory.setSurveyId(surveyid);
        surveyCategory.setSurveyCategoryId(surveycategoryid);
        serviceFactory.getSurveyCategoryService().updateSurveyCategory(surveyid,surveyCategory,session.getAccount().getUsername()); 
   }
   
   @RequestMapping(value="/{surveyid}/categories/{surveycategoryid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_SURVEYSECTION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public SurveyCategory getSurveyCategoryById(@PathVariable("surveyid") UUID surveyid,
		   @PathVariable( "surveycategoryid" ) UUID surveycategoryid,HttpServletRequest request) throws Exception{
       serviceFactory.getSurveyService().getSurveyById(surveyid);
	   return serviceFactory.getSurveyCategoryService().getSurveyCategoryById(surveycategoryid); 
   }

   @RequestMapping(method=RequestMethod.GET,value="/{surveyid}/categories")
   @APIMapping(value="SURVEY_API_GET_ALL_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public SurveyCategories getAllSurveyCategories(@PathVariable("surveyid") UUID surveyid,
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
           serviceFactory.getSurveyService().getSurveyById(surveyid);
        return serviceFactory.getSurveyCategoryService().getAllSurveySurveyCategories(surveyid, startIndex, maxItems);
   }
   @RequestMapping(method=RequestMethod.GET,value="/params/{name}")
   @APIMapping(value="SURVEY_API_GET_ALL_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
   public List<ParamResponse> getSurveyParams(@PathVariable("name") String name,
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
	   if(StringUtils.isNotBlank(name)) {
		   if(StringUtils.equals("version", name)) {
			   return HmisVersionEnum.getPickList();
		   } else if (StringUtils.equals("category", name)) {
			   return SurveyCategoryEnum.getPickList();
		   }
	   }
           return HmisVersionEnum.getPickList();
   }
}