
package com.hserv.coordinatedentry.housingmatching.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;

/**
 * Controller for eligible-clients.
 * 
 */
@RestController
@RequestMapping(value = "/eligible-clients", produces = "application/json")
public class EligibleClientsController {

	@Autowired
	EligibleClientService eligibleClientService;

	/**
	 * Returns the most eligible clients.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@APIMapping(value="get-eligible-clients")
	public List<EligibleClientModel> getEligibleClients() {
		return eligibleClientService.getEligibleClients();
	}

	/**
	 * Update client list. Will accept json having updated details of one or
	 * more clients.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@APIMapping(value="update-eligible-clients")
	public ResponseEntity<String> updateEligibleClients(
			@Validated @RequestBody List<EligibleClientModel> eligibleClientModels) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientService.updateEligibleClients(eligibleClientModels);
			responseEntity = ResponseEntity.ok("{\"updated\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	/**
	 * Deletes the entire eligible-clients list.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@APIMapping(value="delete-eligible-clients")
	public ResponseEntity<String> deleteEligibleClients() {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientService.deleteAll();
			responseEntity = ResponseEntity.ok("{\"deleted\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@APIMapping(value="create-eligible-clients")
	public ResponseEntity<String> createEligibleClients(
			@Validated @RequestBody List<EligibleClientModel> eligibleClientModels) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientService.createEligibleClients(eligibleClientModels);
			responseEntity = ResponseEntity.ok("{\"added\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	/**
	 * Will add client to the eligible list. Accept json having one or more
	 * clients.
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@APIMapping(value="create-eligible-client")
	public ResponseEntity<String> createEligibleClient(
			@Validated @RequestBody EligibleClientModel eligibleClientModel) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientService.createEligibleClient(eligibleClientModel);
			responseEntity = ResponseEntity.ok("{\"added\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	/**
	 * Fetch a particular client.
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@APIMapping(value="get-eligible-client-by-id")
	public EligibleClientModel getEligibleClientById(@PathVariable String id) {
		return eligibleClientService.getEligibleClientDetail(UUID.fromString(id));
	}

	/**
	 * Delete a particular client.
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@APIMapping(value="delete-eligible-client-by-id")
	public ResponseEntity<String> deleteEligibleClientById(@PathVariable String id) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientService.deleteEligibleClientById(UUID.fromString(id));
			responseEntity = ResponseEntity.ok("{\"deleted\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	

	/**
	 * This API will be used to modify the details of a particular client.
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@APIMapping(value="update-eligible-client-by-id")
	public ResponseEntity<String> updateEligibleClientById(@PathVariable String id,
			@Validated @RequestBody EligibleClientModel eligibleClientModel) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientService.updateEligibleClient(UUID.fromString(id), eligibleClientModel);
			responseEntity = ResponseEntity.ok("{\"updated\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
}
