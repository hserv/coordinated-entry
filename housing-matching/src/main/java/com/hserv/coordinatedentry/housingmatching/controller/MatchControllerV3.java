
package com.hserv.coordinatedentry.housingmatching.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.hserv.coordinatedentry.housingmatching.dao.ClientRepository;
import com.hserv.coordinatedentry.housingmatching.entity.Client;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.entity.StatusNotesEntity;
import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.BatchProcessModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchStatusModel;
import com.hserv.coordinatedentry.housingmatching.model.NoteModel;
import com.hserv.coordinatedentry.housingmatching.service.BatchProcessService;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsServiceV3;
import com.hserv.coordinatedentry.housingmatching.translator.MatchReservationTranslator;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.core.model.Session;

@RestController
@RequestMapping(value = "/v3/matches", produces = "application/json")
public class MatchControllerV3 extends BaseController {

	@Autowired
	MatchReservationsServiceV3 matchReservationsService;

	@Autowired
	HousingUnitService inventoryService;
	
	@Autowired
	MatchReservationTranslator matchReservationTranslator;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	
	@Autowired
	private BatchProcessService batchProcessService;
	

	private ResourceAssembler<Match, Resource<MatchReservationModel>> housingInventoryAssembler = new MatchControllerV3.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<Match, Resource<MatchReservationModel>> {

		@Override
		public Resource<MatchReservationModel> toResource(Match arg0) {
			MatchReservationModel model =matchReservationTranslator.translate(arg0);

			Resource<MatchReservationModel> resource = new Resource<MatchReservationModel>(model);
			List<Link> links = new ArrayList<>();
			links.add(new Link("/matches/client/"+model.getEligibleClients().getClientId()+"/status").withRel("history"));
			if(model.getEligibleClients().getLink()!=null)
				links.add(new Link(model.getEligibleClients().getLink()).withRel("client"));
			resource.add(links);
			return resource;
		}
	}	
	
	
	private ResourceAssembler<StatusNotesEntity, Resource<NoteModel>> statuNoteAssembler = new MatchControllerV3.StatusNotesAssembler();
	
	private class StatusNotesAssembler implements ResourceAssembler<StatusNotesEntity, Resource<NoteModel>> {

		@Override
		public Resource<NoteModel> toResource(StatusNotesEntity entity) {
				NoteModel model = new NoteModel();
				model.setNote(entity.getNotes());
				model.setNoteDate(entity.getDateCreated());
				model.setNoteId(entity.getId());
				model.setUserId(entity.getUserId());
				
				Resource<NoteModel> resource = new Resource<NoteModel>(model);
				
			return resource;
		}
	}	
	
	/**
	 * Get the list of the proposed matches.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@APIMapping(value="get-proposed-matches")
	public ResponseEntity<Resources<Resource>> getMatches(@RequestParam(name="q",required=false) String q,
			Pageable pageable,HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		return new ResponseEntity<>(assembler.toResource(matchReservationsService.findAll(q,pageable,session.getAccount().getProjectGroup().getProjectGroupCode()), housingInventoryAssembler),
				HttpStatus.OK);
	}



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
	public void updateMatchByClientId(@PathVariable UUID id,
			@RequestBody MatchReservationModel matchReservationModel) throws Exception {
	
			boolean result = matchReservationsService.updateByClientId(id, matchReservationModel);

	
	}
	
	
	@RequestMapping(value="/client/{id}/status",method=RequestMethod.PUT)
	@APIMapping(value="UPDATE_MATCH_STATUS")
	public void updateMatchStatus(@PathVariable("id") UUID clientId,@RequestBody MatchStatusModel matchStatusModel,HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		String trustedAppId = trustedAppHelper.retrieveTrustedAppId(request);
		matchReservationsService.updateMatchStatus(null,clientId,matchStatusModel,session.getAccount().getUsername(),session,trustedAppId);
	}
	
	@RequestMapping(value="/client/{id}/status",method=RequestMethod.GET)
	@APIMapping(value="UPDATE_MATCH_STATUS")
	public List<MatchStatusModel> getMatchStatus(@PathVariable("id") UUID clientId,HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		String trustedAppId = trustedAppHelper.retrieveTrustedAppId(request);
		return matchReservationsService.getMatchStatusHistory(null,clientId,session.getAccount().getProjectGroup().getProjectGroupCode());
	}
	
}