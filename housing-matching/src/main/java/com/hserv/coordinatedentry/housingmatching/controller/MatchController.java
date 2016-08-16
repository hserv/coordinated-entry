
package com.hserv.coordinatedentry.housingmatching.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;
import com.hserv.coordinatedentry.housingmatching.translator.MatchReservationTranslator;
import com.servinglynk.hmis.warehouse.client.model.Session;

@RestController
@RequestMapping(value = "/matches", produces = "application/json")
public class MatchController extends BaseController {

	@Autowired
	MatchReservationsService matchReservationsService;

	@Autowired
	HousingUnitService inventoryService;
	
	@Autowired
	MatchReservationTranslator matchReservationTranslator;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	

	private ResourceAssembler<Match, Resource<MatchReservationModel>> housingInventoryAssembler = new MatchController.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<Match, Resource<MatchReservationModel>> {

		@Override
		public Resource<MatchReservationModel> toResource(Match arg0) {
			Resource<MatchReservationModel> resource = new Resource<MatchReservationModel>(matchReservationTranslator.translate(arg0));
			/*resource.add(
					linkTo(methodOn(HousingInventoryResource.class).getHousingInverntoryByID(arg0.getHousingInventoryId())).withSelfRel());*/
			return resource;
		}
	}	



	/**
	 * Use this API to trigger the matching process once score calculation is
	 * done. Posting an empty body would suffice.
	 * 
	 * 
	 */
	@RequestMapping( method = RequestMethod.POST)
	@APIMapping(value="trigger-match-process")
	public ResponseEntity<String> createMatch(HttpServletRequest request) throws Exception {
		Session session  = sessionHelper.getSession(request);
		
		matchReservationsService.createMatch(session);
		return ResponseEntity.ok("{\"triggered\": \"success\"}\"");
	}

	/**
	 * Get the list of the proposed matches.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@APIMapping(value="get-proposed-matches")
	public ResponseEntity<Resources<Resource>> getMatches(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResource(matchReservationsService.findAll(pageable), housingInventoryAssembler),
				HttpStatus.OK);
	}


	/**
	 * Clear all the proposed matches.
	 * 
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@APIMapping(value="delete-proposed-matches")
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
	@APIMapping(value="get-match-by-clientId")
	public MatchReservationModel getMatchByClientId(@PathVariable UUID id) {
		return matchReservationsService.findByClientId(id);
	}

	/**
	 * Clears off the proposed match for a particular client.
	 * 
	 * 
	 */
	@RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
	@APIMapping(value="delete-match-by-clientId")
	public ResponseEntity<String> deleteMatchByClientId(@PathVariable UUID id) {
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
	@APIMapping(value="update-match-by-clientId")
	public ResponseEntity<String> updateMatchByClientId(@PathVariable UUID id,
			@RequestBody MatchReservationModel matchReservationModel) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean result = matchReservationsService.updateByClientId(id, matchReservationModel);

			if (result) {
				responseEntity = ResponseEntity.ok("updated : success");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}

		return responseEntity;
	}

}
