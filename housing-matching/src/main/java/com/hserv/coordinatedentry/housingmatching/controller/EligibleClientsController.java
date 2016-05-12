
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
	 * Returns the most eligible clients. URL :
	 * http://localhost:8080/eligible-clients/
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@APIMapping(value="Get_Eligible_Clients")
	public List<EligibleClientModel> getEligibleClients() {
		return eligibleClientService.getEligibleClients();
	}

	/**
	 * Update client list. Will accept json having updated details of one or
	 * more clients.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@APIMapping(value="Update_Eligible_Clients")
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
	@APIMapping(value="Delete_Eligible_Clients")
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
	
	//TODO - Have to get rid of this method, after discussion
	@RequestMapping(value = "", method = RequestMethod.POST)
	@APIMapping(value="Create_Eligible_Clients")
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
	 * URL :- https://localhost:8080/eligible-clients/create
	 * 
	 * { "clientId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12", "surveyScore": 80,
	 * "category": "RRH", "matched": true, "surveyDate": "2016-04-19",
	 * "spdatLabel": "youth" }
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@APIMapping(value="Create_Eligible_Client")
	public ResponseEntity<String> createEligibleClient(
			@Validated @RequestBody EligibleClientModel eligibleClientModel) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientService.createEligibleClient(eligibleClientModel);
			responseEntity = ResponseEntity.ok("{\"added\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);// .notFound().build();
		}
		return responseEntity;
	}

	/**
	 * Fetch a particular client URL :
	 * http://localhost:8080/eligible-clients/a0eebc99-9c0b-4ef8-bb6d-
	 * 6bb9bd380a11
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@APIMapping(value="Get_Eligible_Client_By_Id")
	public EligibleClientModel getEligibleClientById(@PathVariable String id) {
		return eligibleClientService.getEligibleClientDetail(UUID.fromString(id));
	}

	/**
	 * Delete a particular client URL :
	 * http://localhost:8080/eligible-clients/a0eebc99-9c0b-4ef8-bb6d-
	 * 6bb9bd380a12
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@APIMapping(value="Delete_Eligible_Client_By_Id")
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
	 * This API will be used to modify the details of a particular client. URL :
	 * http://localhost:8080/eligible-clients/a0eebc99-9c0b-4ef8-bb6d-
	 * 6bb9bd380a12
	 * 
	 * { "clientId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12", "surveyScore": 80,
	 * "category": "RRH", "matched": true, "surveyDate": "2016-04-19",
	 * "spdatLabel": "family" }
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@APIMapping(value="Update_Eligible_Client_By_Id")
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
