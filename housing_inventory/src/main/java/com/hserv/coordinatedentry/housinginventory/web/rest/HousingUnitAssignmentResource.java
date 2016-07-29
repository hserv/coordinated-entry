package com.hserv.coordinatedentry.housinginventory.web.rest;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URISyntaxException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;
import com.hserv.coordinatedentry.housinginventory.service.HousingUnitAssignmentService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;

@RestController
@RequestMapping("/housing-units")
public class HousingUnitAssignmentResource {

	@Autowired
	HousingUnitAssignmentService housingUnitAssignmentService;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	
	private ResourceAssembler<HousingUnitAssignment, Resource<HousingUnitAssignment>> housingInventoryAssembler = new HousingUnitAssignmentResource.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<HousingUnitAssignment, Resource<HousingUnitAssignment>> {

		@Override
		public Resource<HousingUnitAssignment> toResource(HousingUnitAssignment arg0) {
			Resource<HousingUnitAssignment> resource = new Resource<HousingUnitAssignment>(arg0);
			resource.add(
					linkTo(methodOn(HousingUnitAssignmentResource.class).getHousingInverntoryByID(arg0.getHousingInventory().getHousingInventoryId(),arg0.getAssignmentId())).withSelfRel());
			return resource;
		}
	}
	
	
	@APIMapping(value="CREATE_ASSIGNMENT_BY_HOUSINGUNIT_ID")
	@RequestMapping(value = "/{housingUnitId}/assignments",  method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
	public ResponseEntity<List<HousingUnitAssignment>> createHousingUnitAssignment(@RequestBody List<HousingUnitAssignment> housingUnitAssignments, @PathVariable UUID housingUnitId )
			throws URISyntaxException {
		// log.debug("REST request to save HousingUnitAssignment : {}",
		List<HousingUnitAssignment> housingUnitAssignmentsList=new ArrayList<HousingUnitAssignment>(); 
		HousingInventory housingInventory=new HousingInventory();
		housingInventory.setHousingInventoryId(housingUnitId);
		for(HousingUnitAssignment assignment: housingUnitAssignments){
			assignment.setDateCreated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			assignment.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			assignment.setHousingInventory(housingInventory);
			assignment.setInactive(false);
			assignment.setHousingInventoryId(housingUnitId.toString());
			housingUnitAssignmentsList.add(assignment);
		}
		//List<HousingUnitAssignment> result=new ArrayList<HousingUnitAssignment>();
		List<HousingUnitAssignment> result = housingUnitAssignmentService.saveHousingUnitAssignments(housingUnitAssignmentsList);
		//HousingUnitAssignment result1 = housingUnitAssignmentService.saveHousingUnitAssignment(housingUnitAssignmentsList.get(0));
		//result.add(result1);
		return new ResponseEntity<List<HousingUnitAssignment>>(result,HttpStatus.OK); 
	}

	@APIMapping(value="UPDATE_ASSIGNMENT_BY_HOUSINGUNIT_ID")
	   @RequestMapping(value = "/{housingUnitId}/assignments",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<HousingUnitAssignment>> updateHousingInventory(@RequestBody List<HousingUnitAssignment> housingUnitAssignments, @PathVariable UUID housingUnitId) throws URISyntaxException {
	        //log.debug("REST request to update HousingUnitAssignment : {}", housingUnitAssignment);
	        List<HousingUnitAssignment> result = housingUnitAssignmentService.saveHousingUnitAssignments(housingUnitAssignments);
	        return new ResponseEntity<List<HousingUnitAssignment>>(result,HttpStatus.OK);
	    }
	

	@APIMapping(value="GET_ALL_ASSIGNMENTS_BY_HOUSINGUNIT_ID")
	@RequestMapping(value = "/{housingUnitId}/assignments", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Resources<Resource>> getAllHousingUnitAssignments(@PathVariable UUID housingUnitId,Pageable pageable ) {
		return new ResponseEntity<>(assembler.toResource(housingUnitAssignmentService.getAllHousingUnitAssignments(housingUnitId,pageable), housingInventoryAssembler),
				HttpStatus.OK);
	}

	@APIMapping(value="GET_ASSIGNMENTS_BY_ID")
	@RequestMapping(value = "/{housingUnitId}/assignments/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public HousingUnitAssignment getHousingInverntoryByID(@PathVariable UUID housingUnitId,  @PathVariable UUID id) {
		return housingUnitAssignmentService.getHousingUnitAssignmentById(id);
	}
	
	/*@RequestMapping(value = "/assignments/{id}",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<HousingUnitAssignment> updateHousingInventoryById(@RequestBody HousingUnitAssignment housingUnitAssignment) throws URISyntaxException {
	        //log.debug("REST request to update HousingUnitAssignment : {}", housingUnitAssignment);
	        if (housingUnitAssignment.getAssignmentId()== null) {
	            return createHousingUnitAssignment(housingUnitAssignment);
	        }
	        HousingUnitAssignment result = housingUnitAssignmentService.saveHousingUnitAssignments(housingUnitAssignment);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert("housingUnitAssignment", housingUnitAssignment.getAssignmentId().toString()))
	            .body(result);
	    }*/
	
	@APIMapping(value="DELETE_ASSIGNMENT_BY_ID")
	@RequestMapping(value = "/{housingUnitId}/assignments/{id}",
	        method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    //@Timed
	    public ResponseEntity<Void> deleteHousingUnitAddress(@PathVariable UUID housingUnitId, @PathVariable UUID id) {
	        housingUnitAssignmentService.delete(id);
	        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingUnitAssignment", id.toString())).build();
	    }
	
	
}
