package com.hserv.coordinatedentry.housinginventory.web.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housinginventory.annotation.APIMapping;
import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.service.HousingInventoryService;
import com.servinglynk.hmis.warehouse.client.search.ISearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.Session;

@RestController
@RequestMapping("/v2/housing-units")
public class HousingInventoryResourceV2  extends BaseResource{
	

	@Autowired
	HousingInventoryService housingInventoryService;
	
	 @Autowired
	 ISearchServiceClient searchServiceClient;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	
	
	
private ResourceAssembler<HousingInventory, Resource<HousingInventory>> housingInventoryAssembler = new HousingInventoryResourceV2.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<HousingInventory, Resource<HousingInventory>> {

		@Override
		public Resource<HousingInventory> toResource(HousingInventory arg0) {
			 if(arg0.getSchemaYear()!=null)
		 		arg0.setProject(housingInventoryService.getProjectById(arg0.getProjectId()));
			Resource<HousingInventory> resource = new Resource<HousingInventory>(arg0);
			
				 resource.add(new Link("/hmis-clientapi/rest/v"+arg0.getSchemaYear()+"/projects/"+arg0.getProjectId()).withRel("project"));
			return resource;
		}
	}
	
	
	
	@APIMapping(value="GET_ALL_HOUSING_INVENTORY")
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Resources<Resource>> findAll(@RequestParam(value="inactive" ,required=false,defaultValue="false") Boolean inactive ,
			@RequestParam(value="projectId", required=false) UUID projectId,
			@RequestParam(value="vacant", required=false) Boolean vacant,
			@PageableDefault(size=30)  Pageable pageable,
			HttpServletRequest request) {
		
		Session session = sessionHelper.getSession(request);
		HousingInventory housingInventory=new HousingInventory();
		housingInventory.setProjectId(projectId);
		housingInventory.setVacant(vacant);
		housingInventory.setProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
				
		return new ResponseEntity<>(assembler.toResource(housingInventoryService.getAllHousingInventory(housingInventory, pageable), housingInventoryAssembler),
				HttpStatus.OK);
	}

	
	@APIMapping(value="GET_HOUSING_INVENTORY_BY_ID")
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Resource<HousingInventory>> getHousingInverntoryByID(@PathVariable UUID id) {
		HousingInventory housingInventory = housingInventoryService.getHousingInventoryById(id);
		Resource resource =null;
		 if(housingInventory.getSchemaYear()!=null)
			 housingInventory.setProject(housingInventoryService.getProjectById(housingInventory.getProjectId()));
		if(housingInventory.getSchemaYear()!=null)
			resource = new Resource(housingInventory, (new Link("/hmis-clientapi/rest/v"+housingInventory.getSchemaYear()+"/projects/"+housingInventory.getProjectId()).withRel("project")));
		else
			resource = new Resource(housingInventory);
		return new ResponseEntity<Resource<HousingInventory>>(resource,HttpStatus.OK);
	//	return housingInventoryService.getHousingInventoryById(id);
	}

}
