package com.hserv.coordinatedentry.housinginventory.web.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
				resource = new Resource<EligibilityRequirementModel>(JsonUtil.fromJSON(arg0.getEligibility(), EligibilityRequirementModel.class));
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
		EligibilityRequirement eligibility = new EligibilityRequirement();
		eligibility.setProjectId(projectId);
		eligibility.setEligibility(JsonUtil.toJSON(eligibilityModel));
		housingUnitEligibilityRepository.save(eligibility);
		 
		EligibilityRequirementModel lresult = JsonUtil.fromJSON(eligibility.getEligibility(), EligibilityRequirementModel.class);
		lresult.setEligibilityRequirementId(eligibility.getEligibilityId());
		return new ResponseEntity<EligibilityRequirementModel>(lresult, HttpStatus.OK);

		
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements/{eligibilityId}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateEligibility(@PathVariable UUID projectId,
			@PathVariable UUID eligibilityId,@RequestBody EligibilityRequirementModel eligibilityModel,HttpServletRequest request) throws Exception {
		
		EligibilityRequirement eligibility =  housingUnitEligibilityRepository.findOne(eligibilityId);
		eligibility.setEligibility(JsonUtil.toJSON(eligibilityModel));
		housingUnitEligibilityRepository.save(eligibility);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements/{eligibilityId}",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteEligibility(@PathVariable UUID projectId,
			@PathVariable UUID eligibilityId, HttpServletRequest request) throws Exception {
		EligibilityRequirement eligibility = housingUnitEligibilityRepository.findOne(eligibilityId);
		housingUnitEligibilityRepository.delete(eligibility);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingEligibility", eligibilityId.toString())).build();
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements/{eligibilityId}",method=RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EligibilityRequirementModel getEligibilityById(@PathVariable UUID projectId,
			@PathVariable UUID eligibilityId, HttpServletRequest request) throws Exception {

		EligibilityRequirement eligibility = housingUnitEligibilityRepository.findOne(eligibilityId);
		
		return JsonUtil.fromJSON(eligibility.getEligibility(), EligibilityRequirementModel.class);
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{projectId}/eligibilityrequirements",method=RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<Resource>> getEligibilities(@PathVariable UUID projectId, HttpServletRequest request,Pageable pageable) throws Exception {
		
	  Page<EligibilityRequirement> page	= housingUnitEligibilityRepository.findByProjectId(projectId, pageable);
		return new ResponseEntity<>(assembler.toResource(page, housingInventoryAssembler),
				HttpStatus.OK);
	}
	
}
