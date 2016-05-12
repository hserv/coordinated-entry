package com.hserv.coordinatedentry.housinginventory.web.rest;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;
import com.hserv.coordinatedentry.housinginventory.service.HousingUnitAddressService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import java.net.URI;

@RestController
@RequestMapping("/housing-units")
public class HousingUnitAddressResource {

	@Autowired
	HousingUnitAddressService housingUnitAddressService;

	@RequestMapping(value = "/addresses",  method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
	public ResponseEntity<HousingUnitAddress> createHousingUnitAddress(@RequestBody HousingUnitAddress housingUnitAddress)
			throws URISyntaxException {
		// log.debug("REST request to save HousingUnitAddress : {}",
		if (housingUnitAddress.getAddressId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("housingUnitAddress", "idexists",
					"A new housingUnitAddress cannot already have an ID")).body(null);
		}
		HousingUnitAddress result = housingUnitAddressService.saveHousingUnitAddress(housingUnitAddress);
		return ResponseEntity
				.created(new URI("/api/housing-units/" + result.getAddressId())).headers(HeaderUtil
						.createEntityCreationAlert("housingUnitAddress", result.getAddressId().toString()))
				.body(result);
	}
	
	@RequestMapping(value = "/addresses", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<HousingUnitAddress> getAlladdresses() {
		return housingUnitAddressService.findAll();
	}
	
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
		result.setHousingInventoryId(housingUnitId.toString());
		result.setHousingInventory(null);
		//result.setHousingInventoryId(housingUnitId.toString());
		return ResponseEntity
				.created(new URI("/{housingUnitId}/addresses" + result.getAddressId())).headers(HeaderUtil
						.createEntityCreationAlert("housingUnitAddress", result.getAddressId().toString()))
				.body(result);
	}

	@RequestMapping(value = "/{housingUnitId}/addresses",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<HousingUnitAddress> updateHousingAddress(@RequestBody HousingUnitAddress housingUnitAddress, @PathVariable UUID housingUnitId ) throws URISyntaxException {
	        //log.debug("REST request to update HousingUnitAddress : {}", housingUnitAddress);
	        if (housingUnitAddress.getAddressId() == null) {
	            return createHousingUnitAddressByHousingUnitId(housingUnitAddress, housingUnitId);
	        }
	        HousingUnitAddress result = housingUnitAddressService.saveHousingUnitAddress(housingUnitAddress);
	        result.setHousingInventory(null);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert("housingUnitAddress", housingUnitAddress.getAddressId().toString()))
	            .body(result);
	    }
	
	

	@RequestMapping(value = "/{housingUnitId}/addresses", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<HousingUnitAddress> findAll(@PathVariable UUID housingUnitId) {
		return housingUnitAddressService.getAllHousingUnitAddress(housingUnitId);
	}

	@RequestMapping(value = "{housingUnitId}/addresses/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public HousingUnitAddress getHousingInverntoryByID(@PathVariable UUID housingUnitId,   @PathVariable UUID id) {
		return housingUnitAddressService.getHousingUnitAddressById(id);
	}
	
	@RequestMapping(value = "{housingUnitId}/addresses/{id}",
	        method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<HousingUnitAddress> updateHousingUnitAddressById(@RequestBody HousingUnitAddress housingUnitAddress, @PathVariable UUID housingUnitId) throws URISyntaxException {
	        //log.debug("REST request to update HousingUnitAddress : {}", housingUnitAddress);
	        if (housingUnitAddress.getAddressId()== null) {
	            return createHousingUnitAddress(housingUnitAddress);
	        }
	        HousingUnitAddress result = housingUnitAddressService.saveHousingUnitAddress(housingUnitAddress);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert("housingUnitAddress", housingUnitAddress.getAddressId().toString()))
	            .body(result);
	    }
	
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
