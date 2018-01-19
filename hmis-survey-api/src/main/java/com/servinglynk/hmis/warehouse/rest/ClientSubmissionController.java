package com.servinglynk.hmis.warehouse.rest;

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

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.Error;
//import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.PickListGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/clientsubmission")
public class ClientSubmissionController extends BaseController{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(method=RequestMethod.GET)
	//@APIMapping(value="SURVEY_API_GET_PICKLISTGROUP_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	//public String getPickListGroupById(HttpServletRequest request) throws Exception{
	public Error test() {  
	logger.info("entered get method");    
	Error error = new Error();	
	error.setCode("none");
	error.setMessage("it worked");
	    
	    return error;
	}

}

//@RequestMapping(value="/{picklistgroupid}",method=RequestMethod.GET)
//@APIMapping(value="SURVEY_API_GET_PICKLISTGROUP_BY_ID",checkTrustedApp=true,checkSessionToken=true)
//public PickListGroup getPickListGroupById(@PathVariable( "picklistgroupid" ) UUID PickListGroupId,HttpServletRequest request) throws Exception{
//     return serviceFactory.getPickListGroupService().getPickListGroupById(PickListGroupId); 
//}