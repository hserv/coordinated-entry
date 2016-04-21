
package com.hserv.coordinatedentry.housingmatching.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.facade.EligibleClientFacade;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

/**
 * Controller for eligible-clients.
 * 
 */
@RestController
@RequestMapping(value = "/eligible-clients", produces = "application/json")
public class EligibleClientsController {

	@Autowired
	EligibleClientFacade eligibleClientFacade;

	/**
	 * Returns the most eligible clients. URL :
	 * http://localhost:8080/eligible-clients/all
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<EligibleClientModel> getEligibleClients() {
		return eligibleClientFacade.getEligibleClients();
	}

	/**
	 * Update client list. Will accept json having updated details of one or
	 * more clients.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<String> updateEligibleClients(
			@Validated @RequestBody List<EligibleClientModel> eligibleClientModels) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientFacade.updateEligibleClients(eligibleClientModels);
			responseEntity = ResponseEntity.ok("true");
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
	public ResponseEntity<String> deleteEligibleClients() {
		ResponseEntity<String> responseEntity = null;
		try {
			eligibleClientFacade.deleteAll();
			responseEntity = ResponseEntity.ok("true");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> createEligibleClients(
			@Validated @RequestBody List<EligibleClientModel> eligibleClientModels) {
		ResponseEntity<String> responseEntity = null;
		try {
			eligibleClientFacade.createEligibleClients(eligibleClientModels);
			responseEntity = ResponseEntity.ok("true");
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
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createEligibleClient(
			@Validated @RequestBody EligibleClientModel eligibleClientModel) {
		ResponseEntity<String> responseEntity = null;
		try {
			eligibleClientFacade.createEligibleClient(eligibleClientModel);
			responseEntity = ResponseEntity.ok("true");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("fail", HttpStatus.EXPECTATION_FAILED);// .notFound().build();
		}
		return responseEntity;
	}

	/**
	 * Fetch a particular client URL :
	 * http://localhost:8080/eligible-clients/a0eebc99-9c0b-4ef8-bb6d-
	 * 6bb9bd380a11
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EligibleClientModel getEligibleClientById(@PathVariable String id) {
		return eligibleClientFacade.getEligibleClientDetail(id);
	}

	/**
	 * Delete a particular client URL :
	 * http://localhost:8080/eligible-clients/a0eebc99-9c0b-4ef8-bb6d-
	 * 6bb9bd380a12
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEligibleClientById(@PathVariable String id) {
		ResponseEntity<String> responseEntity = null;
		try {
			eligibleClientFacade.deleteEligibleClientById(id);
			responseEntity = ResponseEntity.ok("Record Deleted");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
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
	public ResponseEntity<String> updateEligibleClientById(@PathVariable String id,
			@Validated @RequestBody EligibleClientModel eligibleClientModel) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientFacade.updateEligibleClient(id, eligibleClientModel);
			responseEntity = ResponseEntity.ok("true");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

}
