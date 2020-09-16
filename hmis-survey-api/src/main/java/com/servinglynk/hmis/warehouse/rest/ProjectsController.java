package com.servinglynk.hmis.warehouse.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.SurveyProjects;

@RestController
@RequestMapping("/projects")
public class ProjectsController extends BaseController {

	  @RequestMapping(method=RequestMethod.GET,value="/{globalprojectid}/surveys")
	   @APIMapping(value="SURVEY_API_GET_ALL_SURVEYSECTION",checkTrustedApp=true,checkSessionToken=true)
	   public SurveyProjects getAllSurveyFromProject(@PathVariable("globalprojectid") UUID globalProjectId,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null || maxItems > 30) maxItems =30;
	        	  
	          return serviceFactory.getSurveyProjectService().getAllSurveyByGlobaProjectId(globalProjectId, startIndex, maxItems);
	   }
	}
