package com.hserv.coordinatedentry.housinginventory.web.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housinginventory.annotation.APIMapping;
import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.service.HousingInventoryService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.servinglynk.hmis.warehouse.client.model.SearchRequest;
import com.servinglynk.hmis.warehouse.client.search.ISearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.BaseProject;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;

@RestController
@RequestMapping("/housing-units")
public class HousingInventoryResource extends BaseResource{

	@Autowired
	HousingInventoryService housingInventoryService;
	
	 @Autowired
	 ISearchServiceClient searchServiceClient;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	

	private ResourceAssembler<HousingInventory, Resource<HousingInventory>> housingInventoryAssembler = new HousingInventoryResource.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<HousingInventory, Resource<HousingInventory>> {

		@Override
		public Resource<HousingInventory> toResource(HousingInventory arg0) {
			arg0.setProject(null);
			Resource<HousingInventory> resource = new Resource<HousingInventory>(arg0);
			
			 if(arg0.getSchemaYear()!=null)
				 resource.add(new Link("/hmis-clientapi/rest/v"+arg0.getSchemaYear()+"/projects/"+arg0.getProjectId()).withRel("project"));
			return resource;
		}
	}
	

	
	private final Logger log = LoggerFactory.getLogger(HousingInventoryResource.class);

	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HousingInventory>> createHousingInventory(@RequestBody List<HousingInventory> housingInventories,HttpServletRequest request)
			throws Exception {
		 log.debug("REST request to save HousingInventory : {}", housingInventories);
		 
		 Session session = sessionHelper.getSession(request);
		 String trustedAppId = trustedAppHelper.retrieveTrustedAppId(request);
		 
		 
		 List<HousingInventory> lHousingInventory  = new ArrayList<HousingInventory>();		 
		 for(HousingInventory inventory : housingInventories){
			 inventory.setProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
			 populateProjectSchemaYear(inventory,session,trustedAppId);
			 HousingInventory result=housingInventoryService.saveHousingInventory(inventory,session);
			 lHousingInventory.add(result);
		 }
		 return new ResponseEntity<List<HousingInventory>>(lHousingInventory, HttpStatus.OK);
	}

	@APIMapping(value="UPDATE_HOUSING_INVENTORIES")
	@RequestMapping(
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<HousingInventory>> updateHousingInventory(@RequestBody List<HousingInventory> housingInventories,HttpServletRequest request) throws URISyntaxException {
	       Session session = sessionHelper.getSession(request);
			List<HousingInventory> lresult = housingInventoryService.updateHousingInentories(housingInventories,session);
	        return new  ResponseEntity<List<HousingInventory>>(lresult, HttpStatus.OK);
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
		housingInventory.setProject(null);
		Resource resource =null;
		if(housingInventory.getSchemaYear()!=null)
			resource = new Resource(housingInventory, (new Link("/hmis-clientapi/rest/v"+housingInventory.getSchemaYear()+"/projects/"+housingInventory.getProjectId()).withRel("project")));
		else
			resource = new Resource(housingInventory);
		return new ResponseEntity<Resource<HousingInventory>>(resource,HttpStatus.OK);
	//	return housingInventoryService.getHousingInventoryById(id);
	}
	
	@APIMapping(value="UPDATE_HOUSING_INVENTORY_BY_ID")
	@RequestMapping(value = "/{id}",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<HousingInventory> updateHousingInventoryById(@RequestBody HousingInventory housingInventory,HttpServletRequest request) throws URISyntaxException {
		Session session = sessionHelper.getSession(request);
		HousingInventory result = housingInventoryService.saveHousingInventory(housingInventory,session);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert("housingInventory", housingInventory.getHousingInventoryId().toString()))
	            .body(result);
	    }
	
	@APIMapping(value="DELETE_HOUSING_INVENTORY")
	@RequestMapping(value = "/{id}",
	        method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    //@Timed
	    public ResponseEntity<Void> deleteHousingInventory(@PathVariable("id") UUID id) {
	        //log.debug("REST request to delete HousingInventory : {}", id);
	        housingInventoryService.delete(id);
	        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingInventory", id.toString())).build();
	    }
	
	
	
	public void populateProjectSchemaYear(HousingInventory inventory,Session session,String trustedAppId) throws Exception {
		 
	 			UUID projectId = inventory.getProjectId();
	 			Map<String,Object> searchParams = new HashMap<>();
	 			searchParams.put("q", projectId);
	 			SearchRequest searchRequest = new SearchRequest();
	 			searchRequest.setSearchParams(searchParams);
	 			searchRequest.setSessionToken(session.getToken());
	 			searchRequest.setTrustedAppId(trustedAppId);
	 			searchRequest.setSearchEntity("projects");
	 			List<BaseProject> projects =  (List<BaseProject>) searchServiceClient.search(searchRequest);
	 			if(!projects.isEmpty())
	 				inventory.setSchemaYear(projects.get(0).getSchemaYear());
	 			else
	 				throw new ResourceNotFoundException("Invalid Project Identification "+projectId);
	 	
	}
}