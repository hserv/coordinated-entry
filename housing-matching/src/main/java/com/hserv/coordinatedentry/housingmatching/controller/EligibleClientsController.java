
package com.hserv.coordinatedentry.housingmatching.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

/**
 * Controller for eligible-clients.
 * 
 */
@RestController
@RequestMapping(value = "/eligible-clients", produces = "application/json")
public class EligibleClientsController {

	/**
	 * Returns the most eligible clients.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity getEligibleClients() {
		return null; 
	}

	/**
	 * Update client list. Will accept json having updated details of one or
	 * more clients.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity updateEligibleClients() {
		return null; 
	}

	/**
	 * Will add client to the eligible list. Accept json having one or more
	 * clients.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity createEligibleClient() {
		return null;
	}

	/**
	 * Deletes the entire eligible-clients list.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity deleteEligibleClients() {
		return null; 
	}

	/**
	 * Fetch a particular client
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EligibleClientModel getEligibleClientById(@PathVariable String id) {
		EligibleClientModel ec = new EligibleClientModel();
		ec.setFirstName("client");
		return ec;
	}

	/**
	 * Delete a particular client
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteEligibleClientById(@PathVariable String id) {
		return null;
	}

	/**
	 * This API will be used to modify the details of a particular client.
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateEligibleClientById(@PathVariable String id) {
		return null;
	}

}
