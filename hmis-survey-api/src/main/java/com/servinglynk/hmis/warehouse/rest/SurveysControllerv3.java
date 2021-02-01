package com.servinglynk.hmis.warehouse.rest; 

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.SurveyCategory;
import com.servinglynk.hmis.warehouse.core.model.Surveysv3;
import com.servinglynk.hmis.warehouse.core.model.Surveyv3;

@RestController
@RequestMapping("/v3/surveys")
public class SurveysControllerv3 extends BaseController { 

   @RequestMapping(method=RequestMethod.POST)
   @APIMapping(value="SURVEY_API_CREATE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public Surveyv3 createSurvey(@Valid @RequestBody Surveyv3 survey,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
         serviceFactory.getSurveyServicev3().createSurvey(survey,session); 
         Set<String> surveyCategories =	survey.getSurveyCategories();
         if(CollectionUtils.isNotEmpty(surveyCategories)) {	
        	Set<SurveyCategory> surveyCategoriesModel = new HashSet<SurveyCategory>();
        	for(String category :  surveyCategories) {
        		SurveyCategory surveyCategory = new SurveyCategory();
        		surveyCategory.setCategory(category);
        		surveyCategoriesModel.add(surveyCategory);
        	}
      	   serviceFactory.getSurveyCategoryService().createSurveyCategory(survey.getSurveyId(), surveyCategoriesModel);
         }
         Surveyv3 returnsurvey = new Surveyv3();
         returnsurvey.setSurveyId(survey.getSurveyId());
         return returnsurvey;
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public void updateSurvey(@PathVariable( "surveyid" ) UUID surveyId,@Valid @RequestBody Surveyv3 survey,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        survey.setSurveyId(surveyId);
        serviceFactory.getSurveyServicev3().updateSurvey(survey,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public void deleteSurvey(@PathVariable( "surveyid" ) UUID surveyId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getSurveyServicev3().deleteSurvey(surveyId,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{surveyid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_SURVEY_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public Surveyv3 getSurveyById(@PathVariable( "surveyid" ) UUID surveyId,HttpServletRequest request) throws Exception{
        return serviceFactory.getSurveyServicev3().getSurveyById(surveyId); 
   }

   @RequestMapping(method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_ALL_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public Surveysv3 getAllSurveys(
                       @RequestParam(value="	", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
           Session session = sessionHelper.getSession(request);
        return serviceFactory.getSurveyServicev3().getAllSurveys(startIndex,maxItems,session.getAccount().getProjectGroup().getProjectGroupCode()); 
   }
}