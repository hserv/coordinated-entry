package com.hserv.coordinatedentry.housinginventory.web.rest;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.net.URI;

@RestController
@RequestMapping("/housing-units")
public class HousingInventoryResource {

	@Autowired
	HousingInventoryService housingInventoryService;
	
	private final Logger log = LoggerFactory.getLogger(HousingInventoryResource.class);

	@APIMapping(value="CREATE_HOUSING_INVENTORIES")
	@RequestMapping(method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HousingInventory>> createHousingInventory(@RequestBody List<HousingInventory> housingInventories)
			throws URISyntaxException {
		 log.debug("REST request to save HousingInventory : {}", housingInventories);
		 if(housingInventories.size()==1){
		HousingInventory result=housingInventoryService.saveHousingInventory(housingInventories.get(0));
		List<HousingInventory> lHousingInventory  = new ArrayList<HousingInventory>();
		lHousingInventory.add(result);
		return new ResponseEntity<List<HousingInventory>>(
				lHousingInventory, HttpStatus.OK);
		 }else{
		 List<HousingInventory> lresult=housingInventoryService.saveHousingInventories(housingInventories);
		 return new ResponseEntity<List<HousingInventory>>(lresult, HttpStatus.OK);
		 }
		
	}

	@APIMapping(value="UPDATE_HOUSING_INVENTORIES")
	@RequestMapping(
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<HousingInventory>> updateHousingInventory(@RequestBody List<HousingInventory> housingInventories) throws URISyntaxException {
		List<HousingInventory> lhousinginventories=new ArrayList<HousingInventory>();
	        for(HousingInventory housingInventory: housingInventories)
	        {
	        	housingInventory.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	        	lhousinginventories.add(housingInventory);
	        }
	        List<HousingInventory> lresult = housingInventoryService.updateHousingInentories(lhousinginventories);
	        return new  ResponseEntity<List<HousingInventory>>(lresult, HttpStatus.OK);
	    }
	
	
	@APIMapping(value="GET_ALL_HOUSING_INVENTORY")
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<HousingInventory> findAll(@RequestParam(value="inactive" ,required=false) Boolean inactive ,
			@RequestParam(value="userId", required=false) String userId,@RequestParam(value="projectId", required=false) String projectId) {
		HousingInventory housingInventory=new HousingInventory();
		housingInventory.setUserId(userId);
		housingInventory.setProjectId(projectId);
		return housingInventoryService.getAllHousingInventory(housingInventory);
	}

	@APIMapping(value="GET_HOUSING_INVENTORY_BY_ID")
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public HousingInventory getHousingInverntoryByID(@PathVariable UUID id) {
		return housingInventoryService.getHousingInventoryById(id);
	}
	
	// TODO Require change
	@APIMapping(value="UPDATE_HOUSING_INVENTORY_BY_ID")
	@RequestMapping(value = "/{id}",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<HousingInventory> updateHousingInventoryById(@RequestBody HousingInventory housingInventory) throws URISyntaxException {
	        HousingInventory result = housingInventoryService.saveHousingInventory(housingInventory);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert("housingInventory", housingInventory.getHousingInventoryId().toString()))
	            .body(result);
	    }
	
	@APIMapping(value="DELETE_HOUSING_INVENTORY")
	@RequestMapping(value = "/{id}",
	        method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    //@Timed
	    public ResponseEntity<Void> deleteHousingInventory(@PathVariable UUID id) {
	        //log.debug("REST request to delete HousingInventory : {}", id);
	        housingInventoryService.delete(id);
	        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingInventory", id.toString())).build();
	    }
	
	
}
