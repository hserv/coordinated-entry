package com.hserv.coordinatedentry.housinginventory.web.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitEligibility;
import com.hserv.coordinatedentry.housinginventory.model.EligibilityModel;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitEligibilityRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.JsonUtil;

@RestController
@RequestMapping("/housing-units")
public class EligibilityResource extends BaseResource {
	
	@Autowired
	HousingUnitEligibilityRepository housingUnitEligibilityRepository;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	

	private ResourceAssembler<HousingUnitEligibility, Resource<EligibilityModel>> housingInventoryAssembler = new EligibilityResource.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<HousingUnitEligibility, Resource<EligibilityModel>> {

		@Override
		public Resource<EligibilityModel> toResource(HousingUnitEligibility arg0) {
			
			Resource<EligibilityModel> resource=null;
			try {
				resource = new Resource<EligibilityModel>(JsonUtil.fromJSON(arg0.getEligibility(), EligibilityModel.class));
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
	@RequestMapping(value="/{housingUnitId}/eligibilities/{projectId}",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EligibilityModel> createEligibility(@PathVariable UUID projectId,@PathVariable UUID housingUnitId,
			@RequestBody EligibilityModel eligibilityModel, HttpServletRequest request) throws Exception {
		HousingUnitEligibility eligibility = new HousingUnitEligibility();
		eligibility.setHousingUnitId(housingUnitId);
		eligibility.setEligibilityId(UUID.randomUUID());
		eligibility.setProjectId(projectId);
		eligibility.setEligibility(JsonUtil.toJSON(eligibilityModel));
		housingUnitEligibilityRepository.save(eligibility);
		 
		EligibilityModel lresult = new EligibilityModel();
		lresult.setEligibiityId(eligibility.getEligibilityId());
		return new ResponseEntity<EligibilityModel>(lresult, HttpStatus.OK);

		
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{housingUnitId}/eligibilities/{projectId}/{eligibilityId}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateEligibility(@PathVariable UUID projectId,@PathVariable UUID housingUnitId,
			@PathVariable UUID eligibilityId,@RequestBody EligibilityModel eligibilityModel,HttpServletRequest request) throws Exception {
		
		HousingUnitEligibility eligibility =  housingUnitEligibilityRepository.findOne(eligibilityId);
		eligibility.setEligibility(JsonUtil.toJSON(eligibilityModel));
		housingUnitEligibilityRepository.save(eligibility);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{housingUnitId}/eligibilities/{projectId}/{eligibilityId}",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteEligibility(@PathVariable UUID projectId,@PathVariable UUID housingUnitId,
			@PathVariable UUID eligibilityId, HttpServletRequest request) throws Exception {
		HousingUnitEligibility eligibility = housingUnitEligibilityRepository.findOne(eligibilityId);
		housingUnitEligibilityRepository.delete(eligibility);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingEligibility", eligibilityId.toString())).build();
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{housingUnitId}/eligibilities/{projectId}/{eligibilityId}",method=RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EligibilityModel getEligibilityById(@PathVariable UUID projectId,@PathVariable UUID housingUnitId,
			@PathVariable UUID eligibilityId, HttpServletRequest request) throws Exception {

		HousingUnitEligibility eligibility = housingUnitEligibilityRepository.findOne(eligibilityId);
		
		return JsonUtil.fromJSON(eligibility.getEligibility(), EligibilityModel.class);
	}
	
	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(value="/{housingUnitId}/eligibilities/{projectId}",method=RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<Resource>> getEligibilities(@PathVariable UUID projectId,@PathVariable UUID housingUnitId, HttpServletRequest request,Pageable pageable) throws Exception {
		
	  Page<HousingUnitEligibility> page	= housingUnitEligibilityRepository.findByHousingUnitIdAndProjectId(housingUnitId, projectId, pageable);
		return new ResponseEntity<>(assembler.toResource(page, housingInventoryAssembler),
				HttpStatus.OK);
	}
	
}
