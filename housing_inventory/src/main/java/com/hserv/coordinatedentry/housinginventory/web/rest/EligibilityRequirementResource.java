package com.hserv.coordinatedentry.housinginventory.web.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housinginventory.annotation.APIMapping;
import com.hserv.coordinatedentry.housinginventory.domain.EligibilityRequirement;
import com.hserv.coordinatedentry.housinginventory.model.EligibilityRequirementModel;
import com.hserv.coordinatedentry.housinginventory.repository.EligibilityRequirementRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.JsonUtil;
import com.servinglynk.hmis.warehouse.core.model.Session;

@RestController
@RequestMapping("/projects")
public class EligibilityRequirementResource extends BaseResource {
	
	@Autowired
	EligibilityRequirementRepository housingUnitEligibilityRepository;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	

	private ResourceAssembler<EligibilityRequirement, Resource<EligibilityRequirementModel>> housingInventoryAssembler = new EligibilityRequirementResource.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<EligibilityRequirement, Resource<EligibilityRequirementModel>> {

		@Override
		public Resource<EligibilityRequirementModel> toResource(EligibilityRequirement arg0) {
			
			Resource<EligibilityRequirementModel> resource=null;
			try {
				EligibilityRequirementModel model = JsonUtil.fromJSON(arg0.getEligibility(), EligibilityRequirementModel.class);
				model.setProjectId(arg0.getProjectId());
				model.setEligibilityRequirementId(arg0.getEligibilityId());
				resource = new Resource<EligibilityRequirementModel>(model);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*resource.add(
					linkTo(methodOn(HousingInventoryResource.class).getHousingInverntoryByID(arg0.getHousingInventoryId())).withSelfRel());*/
			return resource;
		}
	}
	


	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EligibilityRequirementModel> createEligibility(@PathVariable UUID projectId,
			@Valid @RequestBody EligibilityRequirementModel eligibilityModel, HttpServletRequest request) throws Exception {

		Session session = sessionHelper.getSession(request);
		EligibilityRequirement eligibility = new EligibilityRequirement();
		eligibility.setProjectId(projectId);
		eligibility.setEligibility(JsonUtil.toJSON(eligibilityModel));
		eligibility.setProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
		housingUnitEligibilityRepository.save(eligibility);
		 
		EligibilityRequirementModel lresult = JsonUtil.fromJSON(eligibility.getEligibility(), EligibilityRequirementModel.class);
		lresult.setEligibilityRequirementId(eligibility.getEligibilityId());
		return new ResponseEntity<EligibilityRequirementModel>(lresult, HttpStatus.OK);

		
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements/{eligibilityId}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateEligibility(@PathVariable UUID projectId,
			@PathVariable UUID eligibilityId,@RequestBody EligibilityRequirementModel eligibilityModel,HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		EligibilityRequirement eligibility =  housingUnitEligibilityRepository.findOne(eligibilityId);
		eligibility.setEligibility(JsonUtil.toJSON(eligibilityModel));
		eligibility.setProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
		housingUnitEligibilityRepository.save(eligibility);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements/{eligibilityId}",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteEligibility(@PathVariable UUID projectId,
			@PathVariable UUID eligibilityId, HttpServletRequest request) throws Exception {
		EligibilityRequirement eligibility = housingUnitEligibilityRepository.findOne(eligibilityId);
		if(eligibility==null) throw new ResourceNotFoundException("Eligibility requirement not found "+eligibilityId);
		housingUnitEligibilityRepository.delete(eligibility);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingEligibility", eligibilityId.toString())).build();
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements/{eligibilityId}",method=RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EligibilityRequirementModel getEligibilityById(@PathVariable UUID projectId,
			@PathVariable UUID eligibilityId, HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		EligibilityRequirement eligibility = housingUnitEligibilityRepository.findByEligibilityIdAndProjectGroupCodeAndDeleted(eligibilityId,session.getAccount().getProjectGroup().getProjectGroupCode(),false);		
		return JsonUtil.fromJSON(eligibility.getEligibility(), EligibilityRequirementModel.class);
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements",method=RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<Resource>> getEligibilities(@PathVariable UUID projectId, HttpServletRequest request,Pageable pageable) throws Exception {
		Session session = sessionHelper.getSession(request);
	  Page<EligibilityRequirement> page	= housingUnitEligibilityRepository.findByProjectIdAndProjectGroupCodeAndDeleted(projectId,session.getAccount().getProjectGroup().getProjectGroupCode(),false ,pageable);
		return new ResponseEntity<>(assembler.toResource(page, housingInventoryAssembler),
				HttpStatus.OK);
	}
	@APIMapping(value="GET_ALL_PROJECT_ELIGIBILITY")  
	@RequestMapping(value="/eligibilityrequirements",method=RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<Resource>> getAllEligibilities(HttpServletRequest request,Pageable pageable) throws Exception {
		Session session = sessionHelper.getSession(request);
		//Adding S.o.P for debugging will take it out
		System.out.println("session.getAccount()"+session.getAccount().toString());
		System.out.println("session.getAccount().getProjectGroup()"+session.getAccount().getProjectGroup().toString());
		System.out.println("session.getAccount().getProjectGroup().getProjectGroupCode()"+session.getAccount().getProjectGroup().getProjectGroupCode());
		
	  Page<EligibilityRequirement> page	= housingUnitEligibilityRepository.findByProjectGroupCodeAndDeleted(session.getAccount().getProjectGroup().getProjectGroupCode(), false,pageable);
		return new ResponseEntity<>(assembler.toResource(page, housingInventoryAssembler),
				HttpStatus.OK);
	}
	
}
