
package com.hserv.coordinatedentry.housingmatching.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.external.UserService;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;
import com.hserv.coordinatedentry.housingmatching.service.community.monterey.MontereyCommunityStrategy;

@RestController
@RequestMapping(value = "/matches", produces = "application/json")
public class MatchController {

	@Autowired
	MatchReservationsService matchReservationsService;

	@Autowired
	HousingUnitService inventoryService;


	/**
	 * Use this API to trigger the matching process once score calculation is
	 * done. Posting an empty body would suffice.
	 * 
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> createMatch() {
		matchReservationsService.createMatch();
		return ResponseEntity.ok("triggered : success");
	}

	/**
	 * Get the list of the proposed matches.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Set<MatchReservationModel> getMatches() {
		return matchReservationsService.findAll();
	}

	/*@RequestMapping(value = "/houseinventory", method = RequestMethod.GET)
	public List<HousingInventoryModel> testGetInventory() {
		return inventoryService.getHousingInventoryList(null, false, null);
	}*/

	/**
	 * Clear all the proposed matches.
	 * 
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMatches() {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean result = matchReservationsService.deleteAll();
			if (result) {
				responseEntity = ResponseEntity.ok("deleted all matches : success");
			}

		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	/**
	 * Get the proposed match for a client_id.
	 * 
	 */
	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
	public MatchReservationModel getClientById(@PathVariable String id) {
		return matchReservationsService.findByClientId(id);
	}

	/**
	 * Clears off the proposed match for a particular client.
	 * 
	 * 
	 */
	@RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteClientById(@PathVariable String id) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean result = matchReservationsService.deleteByClientId(id);
			if (result) {
				responseEntity = ResponseEntity.ok("deleted : success");
			}

		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	/**
	 * Used for updating/accepting the match for a client id. This method will
	 * be called as and when the Admin approves or reject the housing match for
	 * client
	 * 
	 */
	@RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateClientById(@PathVariable String id,
			@RequestBody MatchReservationModel matchReservationModel) {
		// call DB layer to update match_reservation table with match_status
		// as approve or rejected based on the input value
		ResponseEntity<String> responseEntity = null;
		try {
			boolean result = matchReservationsService.updateByClientId(id, matchReservationModel);

			if (result) {
				responseEntity = ResponseEntity.ok("updated : success");
			}

		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}

		return responseEntity;
	}

}
