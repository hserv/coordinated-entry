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
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.Surveysv2;
import com.servinglynk.hmis.warehouse.core.model.Surveyv2;

@RestController
@RequestMapping("/v2/surveys")
public class SurveysControllerv2 extends BaseController { 

   @RequestMapping(method=RequestMethod.POST)
   @APIMapping(value="SURVEY_API_CREATE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public Surveyv2 createSurvey(@Valid @RequestBody Surveyv2 survey,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
         serviceFactory.getSurveyServicev2().createSurvey(survey,session); 
         Surveyv2 returnsurvey = new Surveyv2();
         returnsurvey.setSurveyId(survey.getSurveyId());
         return returnsurvey;
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public void updateSurvey(@PathVariable( "surveyid" ) UUID surveyId,@Valid @RequestBody Surveyv2 survey,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        survey.setSurveyId(surveyId);
        serviceFactory.getSurveyServicev2().updateSurvey(survey,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public void deleteSurvey(@PathVariable( "surveyid" ) UUID surveyId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getSurveyServicev2().deleteSurvey(surveyId,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_SURVEY_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public Surveyv2 getSurveyById(@PathVariable( "surveyid" ) UUID surveyId,HttpServletRequest request) throws Exception{
        return serviceFactory.getSurveyServicev2().getSurveyById(surveyId); 
   }

   @RequestMapping(method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_ALL_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public Surveysv2 getAllSurveys(
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
           Session session = sessionHelper.getSession(request);
        return serviceFactory.getSurveyServicev2().getAllSurveys(startIndex,maxItems,session.getAccount().getProjectGroup().getProjectGroupCode()); 
   }
}