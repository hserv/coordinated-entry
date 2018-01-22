package com.servinglynk.hmis.warehouse.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.ClientSubmission;
//import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.PickListGroup;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.Survey;
import com.servinglynk.hmis.warehouse.model.ClientSubmissionEntity;
import com.servinglynk.hmis.warehouse.util.RestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RestController
@RequestMapping("/clientsubmission")
public class ClientSubmissionController extends BaseController{
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RestClient restClient;
	
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	@APIMapping(value="SURVEY_API_CREATE_SUBMISSION",checkTrustedApp=true,checkSessionToken=true)
	public ClientSubmissionEntity create(HttpServletRequest request) throws Exception{  
		logger.debug("entered get method");    
		final ClientSubmissionEntity clientSubmissionEntity = new ClientSubmissionEntity();
		clientSubmissionEntity.setClientId(UUID.randomUUID());
		clientSubmissionEntity.setSurveyId(UUID.randomUUID());
		clientSubmissionEntity.setSubmissionId(UUID.randomUUID());
		serviceFactory.getClientSubmissionService().createClientSubmissionEntity(clientSubmissionEntity);
	    // call method to retrieve globalEnrollmentId
		// ...
		logger.debug("called update method");
		UUID globalEnrollmentId = UUID.randomUUID();
		clientSubmissionEntity.setGlobalEnrollmentId(globalEnrollmentId);
		serviceFactory.getClientSubmissionService().updateClientSubmissionEntity(clientSubmissionEntity);
		
		return clientSubmissionEntity;
	}
	
	@RequestMapping(value="/update/{submissionId}",method=RequestMethod.PUT)
	@APIMapping(value="SURVEY_API_UPDATE_SUBMISSION",checkTrustedApp=true,checkSessionToken=true)
	public ClientSubmissionEntity update(@PathVariable( "submissionId" ) UUID submissionId, HttpServletRequest request) throws Exception{  
		logger.debug("entered create method"); 
		final ClientSubmissionEntity clientSubmissionEntity = new ClientSubmissionEntity();
		
		
		
		return clientSubmissionEntity;
	}	
	
	@RequestMapping(value="/new",method=RequestMethod.GET)
	@APIMapping(value="SURVEY_API_CREATE_SUBMISSION",checkTrustedApp=true,checkSessionToken=true)
	public ClientSubmissionEntity createList(HttpServletRequest request) throws Exception{  
		logger.debug("entered new method");    
		List<ClientSubmissionEntity> csss = new ArrayList<ClientSubmissionEntity>();
		
		
		ClientSubmissionEntity clientSubmissionEntity = new ClientSubmissionEntity();
		clientSubmissionEntity.setClientId(UUID.randomUUID());
		clientSubmissionEntity.setSurveyId(UUID.randomUUID());
		clientSubmissionEntity.setSubmissionId(UUID.randomUUID());
		
		csss.add(clientSubmissionEntity);
		clientSubmissionEntity.setSubmissionId(UUID.randomUUID());
		csss.add(clientSubmissionEntity);
		clientSubmissionEntity.setSubmissionId(UUID.randomUUID());
		csss.add(clientSubmissionEntity);
		
		logger.debug("List size= {}",csss.size()); 
		
		
		//serviceFactory.getClientSubmissionService().createClientSubmissionEntity(csss);
	    // call method to retrieve globalEnrollmentId
		// ...
		/*
		
		logger.debug("called update method");
		UUID globalEnrollmentId = UUID.randomUUID();
		clientSubmissionEntity.setGlobalEnrollmentId(globalEnrollmentId);
		serviceFactory.getClientSubmissionService().updateClientSubmissionEntity(clientSubmissionEntity);
		*/
		
			csss.stream().forEach((cs) ->{
			
			restClient.get("http://localhost:8080/rest/",ResponseType )
			
			logger.debug("created {}",cs);
		});
		
		
		
		
		return clientSubmissionEntity;
	}
	
	
	}
	
/*
 * @RequestMapping(value="/{surveyid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_SURVEY",checkTrustedApp=true,checkSessionToken=true)
   public void updateSurvey(@PathVariable( "surveyid" ) UUID surveyId,@Valid @RequestBody Survey survey,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        survey.setSurveyId(surveyId);
        serviceFactory.getSurveyService().updateSurvey(survey,session.getAccount().getUsername()); 
   }
 * */
