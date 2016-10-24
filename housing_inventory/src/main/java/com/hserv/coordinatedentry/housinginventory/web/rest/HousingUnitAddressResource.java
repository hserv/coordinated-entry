package com.hserv.coordinatedentry.housinginventory.web.rest;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
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
import com.hserv.coordinatedentry.housinginventory.service.HousingUnitAddressService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;

@RestController
@RequestMapping("/housing-units")
public class HousingUnitAddressResource extends BaseResource{

	@Autowired
	HousingUnitAddressService housingUnitAddressService;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	
	private ResourceAssembler<HousingUnitAddress, Resource<HousingUnitAddress>> housingInventoryAssembler = new HousingUnitAddressResource.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<HousingUnitAddress, Resource<HousingUnitAddress>> {

		@Override
		public Resource<HousingUnitAddress> toResource(HousingUnitAddress arg0) {
			Resource<HousingUnitAddress> resource = new Resource<HousingUnitAddress>(arg0);
			return resource;
		}
	}
	
	@APIMapping(value="CREATE_ADDRESSES")
	@RequestMapping(value = "/{housingUnitId}/addresses",  method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
	public ResponseEntity<HousingUnitAddress> createHousingUnitAddressByHousingUnitId(@RequestBody HousingUnitAddress housingUnitAddress, @PathVariable UUID housingUnitId)
			throws URISyntaxException {
		// log.debug("REST request to save HousingUnitAddress : {}",
		if (housingUnitAddress.getAddressId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("housingUnitAddress", "idexists",
					"A new housingUnitAddress cannot already have an ID")).body(null);
		}
		HousingInventory housingInventory=new HousingInventory();
		housingInventory.setHousingInventoryId(housingUnitId);
		housingUnitAddress.setHousingInventory(housingInventory);
		HousingUnitAddress result = housingUnitAddressService.saveHousingUnitAddress(housingUnitAddress);
		result.setHousingInventory(null);
		return ResponseEntity
				.created(new URI("/"+housingUnitId+"/addresses" + result.getAddressId())).headers(HeaderUtil
						.createEntityCreationAlert("housingUnitAddress", result.getAddressId().toString()))
				.body(result);
	}

	@APIMapping(value="UPDATE_ADDRESSES_BY_HOUSING_UNIT")
	@RequestMapping(value = "/{housingUnitId}/addresses",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<HousingUnitAddress> updateHousingAddress(@RequestBody HousingUnitAddress housingUnitAddress, @PathVariable UUID housingUnitId ) throws URISyntaxException {
	        //log.debug("REST request to update HousingUnitAddress : {}", housingUnitAddress);
			HousingInventory inventory = new HousingInventory();
			inventory.setHousingInventoryId(housingUnitId);
			housingUnitAddress.setHousingInventory(inventory);
	        if (housingUnitAddress.getAddressId() == null) {
	            return createHousingUnitAddressByHousingUnitId(housingUnitAddress, housingUnitId);
	        }
	        HousingUnitAddress result = housingUnitAddressService.saveHousingUnitAddress(housingUnitAddress);
	        result.setHousingInventory(null);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert("housingUnitAddress", housingUnitAddress.getAddressId().toString()))
	            .body(result);
	    }
	
	
	@APIMapping(value="GET_ALL_HISTORICAL_ADDRESS_BY_HOUSING_UNIT_ID")
	@RequestMapping(value = "/{housingUnitId}/addresses", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Resources<Resource>> findAll(@PathVariable UUID housingUnitId,Pageable pageable) {
		return new ResponseEntity<>(assembler.toResource(housingUnitAddressService.getAllHousingUnitAddress(housingUnitId,pageable), housingInventoryAssembler),
				HttpStatus.OK);
	}

	@APIMapping(value="GET_ADDRESS_BY_ID")
	@RequestMapping(value = "{housingUnitId}/addresses/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public HousingUnitAddress getHousingInverntoryByID(@PathVariable UUID housingUnitId,   @PathVariable UUID id) {
		return housingUnitAddressService.getHousingUnitAddressById(id);
	}
	
	@APIMapping(value="UPDATE_ADDRESS_BY_ID")
	@RequestMapping(value = "{housingUnitId}/addresses/{id}",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<HousingUnitAddress> updateHousingUnitAddressById(@RequestBody HousingUnitAddress housingUnitAddress, @PathVariable UUID housingUnitId) throws URISyntaxException {
	        HousingUnitAddress result = housingUnitAddressService.saveHousingUnitAddress(housingUnitAddress);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert("housingUnitAddress", housingUnitAddress.getAddressId().toString()))
	            .body(result);
	    }
	
	@APIMapping(value="DELETE_ADDRESS_BY_ID")
	@RequestMapping(value = "/addresses/{id}",
	        method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    //@Timed
	    public ResponseEntity<Void> deleteHousingUnitAddress(@PathVariable UUID id) {
	        //log.debug("REST request to delete HousingUnitAddress : {}", id);
		   
	        housingUnitAddressService.delete(id);
	        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingUnitAddress", id.toString())).build();
	    }
	
	
}
