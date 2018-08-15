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
import com.servinglynk.hmis.warehouse.core.model.PickListGroup;
import com.servinglynk.hmis.warehouse.core.model.PickListGroups;
import com.servinglynk.hmis.warehouse.core.model.PickListValue;
import com.servinglynk.hmis.warehouse.core.model.PickListValues;
import com.servinglynk.hmis.warehouse.core.model.PickListValues2;

@RestController
@RequestMapping("/picklistgroups")
public class PickListGroupsController extends BaseController { 

   @RequestMapping(method=RequestMethod.POST)
   @APIMapping(value="SURVEY_API_CREATE_PICKLISTGROUP",checkTrustedApp=true,checkSessionToken=true)
   public PickListGroup createPickListGroup(@Valid @RequestBody PickListGroup PickListGroup,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
         serviceFactory.getPickListGroupService().createPickListGroup(PickListGroup,session.getAccount().getUsername()); 
         PickListGroup returnPickListGroup = new PickListGroup();
         returnPickListGroup.setPickListGroupId(PickListGroup.getPickListGroupId());
         return returnPickListGroup;
   }

   @RequestMapping(value="/{picklistgroupid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_PICKLISTGROUP",checkTrustedApp=true,checkSessionToken=true)
   public void updatePickListGroup(@PathVariable( "picklistgroupid" ) UUID PickListGroupId,@Valid @RequestBody PickListGroup PickListGroup,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        PickListGroup.setPickListGroupId(PickListGroupId);
        serviceFactory.getPickListGroupService().updatePickListGroup(PickListGroup,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{picklistgroupid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_PICKLISTGROUP",checkTrustedApp=true,checkSessionToken=true)
   public void deletePickListGroup(@PathVariable( "picklistgroupid" ) UUID PickListGroupId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getPickListGroupService().deletePickListGroup(PickListGroupId,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{picklistgroupid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_PICKLISTGROUP_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public PickListGroup getPickListGroupById(@PathVariable( "picklistgroupid" ) UUID PickListGroupId,HttpServletRequest request) throws Exception{
        return serviceFactory.getPickListGroupService().getPickListGroupById(PickListGroupId); 
   }

   @RequestMapping(method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_ALL_PICKLISTGROUP",checkTrustedApp=true,checkSessionToken=true)
   public PickListGroups getAllPickListGroups(
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
 
 
        return serviceFactory.getPickListGroupService().getAllPickListGroups(startIndex,maxItems); 
   }

   
   
   @RequestMapping(value="/{picklistgroupid}/picklistvalues",method=RequestMethod.POST)
   @APIMapping(value="SURVEY_API_CREATE_PICKLISTVALUE",checkTrustedApp=true,checkSessionToken=true)
   public PickListValues createPickListValue(@Valid @PathVariable( "picklistgroupid" ) UUID pickListGroupId,
		   @RequestBody PickListValues pickListValues,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
       return serviceFactory.getPickListValueService().createPickListValue(pickListGroupId,pickListValues,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{picklistgroupid}/picklistvalues/{picklistvalueid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_PICKLISTVALUE",checkTrustedApp=true,checkSessionToken=true)
   public void updatePickListValue(@PathVariable( "picklistgroupid" ) UUID pickListGroupId,
		   @PathVariable( "picklistvalueid" ) UUID PickListValueId,@Valid  @RequestBody PickListValue PickListValue,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        PickListValue.setPickListValueId(PickListValueId);
        serviceFactory.getPickListGroupService().getPickListGroupById(pickListGroupId);
        serviceFactory.getPickListValueService().updatePickListValue(PickListValue,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{picklistgroupid}/picklistvalues/{picklistvalueid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_PICKLISTVALUE",checkTrustedApp=true,checkSessionToken=true)
   public void deletePickListValue(@PathVariable( "picklistgroupid" ) UUID pickListGroupId,
		   @PathVariable( "picklistvalueid" ) UUID PickListValueId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getPickListGroupService().getPickListGroupById(pickListGroupId);
        serviceFactory.getPickListValueService().deletePickListValue(PickListValueId,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{picklistgroupid}/picklistvalues/{picklistvalueid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_PICKLISTVALUE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public PickListValue getPickListValueById(@PathVariable( "picklistgroupid" ) UUID pickListGroupId,
		   @PathVariable( "picklistvalueid" ) UUID PickListValueId,HttpServletRequest request) throws Exception{
       serviceFactory.getPickListGroupService().getPickListGroupById(pickListGroupId);
	   return serviceFactory.getPickListValueService().getPickListValueById(PickListValueId); 
   }

   @RequestMapping(value="/{picklistgroupid}/picklistvalues",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_ALL_PICKLISTVALUE",checkTrustedApp=true,checkSessionToken=true)
   public PickListValues2 getAllPickListValues(@PathVariable( "picklistgroupid" ) UUID pickListGroupId,
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
           serviceFactory.getPickListGroupService().getPickListGroupById(pickListGroupId);
        return serviceFactory.getPickListValueService().getAllPickListGroupPickListValues(pickListGroupId,startIndex,maxItems); 
   }
}

