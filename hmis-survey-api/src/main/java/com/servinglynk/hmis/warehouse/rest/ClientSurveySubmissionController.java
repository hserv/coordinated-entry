package com.servinglynk.hmis.warehouse.rest;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
//import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.PickListGroup;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.Survey;
import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.util.RestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/clientsurveysubmission")
public class ClientSurveySubmissionController extends BaseController{
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RestClient restClient;
	
	@RequestMapping(method=RequestMethod.GET)
	@APIMapping(value="SURVEY_API_CREATE_CLIENT_SURVEY_SUBMISSION",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmissionEntity create(HttpServletRequest request) throws Exception{  
		logger.debug("entered get method");    
		// to be completed with path variable
		ClientSurveySubmissionEntity clientSurveySubmissionEntity = new ClientSurveySubmissionEntity();
		clientSurveySubmissionEntity.setClientId(UUID.randomUUID());
		clientSurveySubmissionEntity.setSurveyId(UUID.randomUUID());
		clientSurveySubmissionEntity.setSubmissionId(UUID.randomUUID());
		ClientSurveySubmissionEntity result = serviceFactory.getClientSurveySubmissionService().createClientSurveySubmissionEntity(clientSurveySubmissionEntity);
	    return result;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@APIMapping(value="SURVEY_API_CLIENT_SURVEY_SUBMISSION",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmissionEntity create(@RequestBody ClientSurveySubmission clientSurveySubmission,HttpServletRequest request) throws Exception{  
	
		final ClientSurveySubmissionEntity clientSurveySubmissionEntity = new ClientSurveySubmissionEntity();
		clientSurveySubmissionEntity.setClientId(clientSurveySubmission.getClientId());
		clientSurveySubmissionEntity.setSurveyId(clientSurveySubmission.getSurveyId());
		clientSurveySubmissionEntity.setSubmissionId(clientSurveySubmission.getSubmissionId());
		ClientSurveySubmissionEntity result = serviceFactory.getClientSurveySubmissionService().createClientSurveySubmissionEntity(clientSurveySubmissionEntity);
	    return result;
	}
	
	@RequestMapping(value="/update/id/{clientsurveysubmissionId}/globalenrollmentId/{globalenrollmentId}",method=RequestMethod.GET)
	@APIMapping(value="SURVEY_API_UPDATE_SUBMISSION",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmissionEntity update(@PathVariable( "clientsurveysubmissionId" ) UUID id, @PathVariable( "globalenrollmentId" ) UUID globalenrollmentId, 
		HttpServletRequest request) throws Exception{  
		logger.debug("entered create method"); 
		ClientSurveySubmissionEntity clientSurveySubmissionEntity = 
				serviceFactory.getClientSurveySubmissionService().getClientSurveySubmissionEntitybyId(id);
		clientSurveySubmissionEntity.setGlobalEnrollmentId(globalenrollmentId);
		ClientSurveySubmissionEntity result = serviceFactory.getClientSurveySubmissionService().updateClientSurveySubmissionEntity(clientSurveySubmissionEntity);
		
		return result;
	}	
	
	@RequestMapping(value="/new",method=RequestMethod.GET)
	@APIMapping(value="SURVEY_API_CREATE_SUBMISSION",checkTrustedApp=true,checkSessionToken=true)
	public ClientSurveySubmission createList(HttpServletRequest request) throws Exception{  
		logger.debug("entered new method");    
		List<ClientSurveySubmission> csss = new ArrayList<ClientSurveySubmission>();
		
		// simulate data obtained from table response
		ClientSurveySubmission clientSubmission1 = new ClientSurveySubmission();
		ClientSurveySubmission clientSubmission2 = new ClientSurveySubmission();
		ClientSurveySubmission clientSubmission3 = new ClientSurveySubmission();
		
		clientSubmission1.setClientId(UUID.randomUUID());
		clientSubmission1.setSurveyId(UUID.randomUUID());
		clientSubmission1.setSubmissionId(UUID.randomUUID());
		
		clientSubmission2.setClientId(clientSubmission1.getClientId());
		clientSubmission2.setSurveyId(clientSubmission1.getSurveyId());
		clientSubmission2.setSubmissionId(UUID.randomUUID());
		
		clientSubmission3.setClientId(clientSubmission1.getClientId());
		clientSubmission3.setSurveyId(clientSubmission1.getSurveyId());
		clientSubmission3.setSubmissionId(UUID.randomUUID());
		csss.add(clientSubmission1);
		csss.add(clientSubmission2);
		csss.add(clientSubmission3);
		
		// for testing hack to resolve jsonrootname not provided by mapper
		/*
		HashMap <String,ClientSurveySubmission> payLoad = new HashMap();
				
		logger.debug("List size= {}",csss.size()); 
				
		csss.stream().forEach((cs) ->{
			logger.debug("creating {}",cs.toString() );
			payLoad.put("ClientSubmission",cs);
			restClient.post("http://localhost:8080/survey-api/rest/clientsubmission",payLoad, ClientSurveySubmission.class);
			
			logger.debug("created {}",cs.toString() );
			
		});
		 */		
		ClientSurveySubmissionEntity clientSurveySubmissionEntity = new ClientSurveySubmissionEntity();
		csss.stream().forEach((cs) ->{
			logger.debug("creating {}",cs.toString() );
			clientSurveySubmissionEntity.setClientId(cs.getClientId());
			clientSurveySubmissionEntity.setSurveyId(cs.getSurveyId());
			clientSurveySubmissionEntity.setSubmissionId(cs.getSubmissionId());
			serviceFactory.getClientSurveySubmissionService().createClientSurveySubmissionEntity(clientSurveySubmissionEntity);
			// need to add error logging and save to file?
			logger.debug("created {}",cs.toString() );
		});
		
		return csss.get(0);
	}
	
	
	}
	

