
package com.hserv.coordinatedentry.housingmatching.controller;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.ClientModel;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientAuditsModel;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientsModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.translator.EligibleClientsTranslator;
import com.servinglynk.hmis.warehouse.core.model.Session;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Controller for eligible-clients.
 * 
 */
@RestController
@RequestMapping(value = "/eligibleclients", produces = "application/json")
public class EligibleClientsController extends BaseController {

	@Autowired
	EligibleClientService eligibleClientService;
	
	
	@Autowired
	private PagedResourcesAssembler assembler;
	
	@Autowired
	private EligibleClientsTranslator eligibleClientsTranslator;
	

	private ResourceAssembler<EligibleClient, Resource<EligibleClientModel>> housingInventoryAssembler = new EligibleClientsController.HousingInventoryAssembler();
	
	private class HousingInventoryAssembler implements ResourceAssembler<EligibleClient, Resource<EligibleClientModel>> {

		@Override
		public Resource<EligibleClientModel> toResource(EligibleClient arg0) {
			Resource<EligibleClientModel> resource = new Resource<EligibleClientModel>(eligibleClientsTranslator.translate(arg0));
			if(arg0.getClientLink()!=null)
				resource.add(new Link(arg0.getClientLink()).withRel("client"));
			return resource;
		}
	}	
	

	/**
	 * Returns the most eligible clients.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@APIMapping(value="get-eligible-clients")
	public ResponseEntity<Resources<Resource>> getEligibleClients(Pageable pageable,HttpServletRequest request,
			@RequestParam(defaultValue = "active",required=false) String filter) throws Exception {
		Session session = sessionHelper.getSession(request);
		String projectGroupCode = session.getAccount().getProjectGroup().getProjectGroupCode();
		Link statusLink = linkTo(methodOn(BatchProcessController.class).getCurrentStatus(request)).withRel("scoreprocessingstatus");
		PageRequest page= new PageRequest(0,20);
		Link historyLink = linkTo(methodOn(BatchProcessController.class).getBatchProcessHistory(page, request)).withRel("scoreprocessinghistory");

		PagedResources resources = assembler.toResource(eligibleClientService.getEligibleClients(projectGroupCode,pageable,filter), housingInventoryAssembler);
		resources.getLinks().add(statusLink);
		resources.getLinks().add(historyLink);
		return new ResponseEntity<>(resources,
				HttpStatus.OK);
	}

	/**
	 * Update client list. Will accept json having updated details of one or
	 * more clients.
	 * 
	 */
/*	@RequestMapping(value = "", method = RequestMethod.PUT)
	@APIMapping(value="update-eligible-clients")
	public ResponseEntity<String> updateEligibleClients(
			@Valid @RequestBody EligibleClientsModel eligibleClientModels) throws Exception  {
		ResponseEntity<String> responseEntity = null;
			boolean status = eligibleClientService.updateEligibleClients(eligibleClientModels.getEligibleClients());
			responseEntity = ResponseEntity.ok("{\"updated\": \""+ status +"\"}\"");
		return responseEntity;
	}*/

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
			@Valid @RequestBody EligibleClientsModel eligibleClientModels) throws Exception {
		ResponseEntity<String> responseEntity = null;
			boolean status = eligibleClientService.createEligibleClients(eligibleClientModels.getEligibleClients());
			responseEntity = ResponseEntity.ok("{\"added\": \""+ status +"\"}\"");
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
			@Valid @RequestBody EligibleClientModel eligibleClientModel) {
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
	public ResponseEntity<Resource>  getEligibleClientById(@PathVariable UUID id) {
		EligibleClientModel client =  eligibleClientService.getEligibleClientDetail(id,"");
		Resource<EligibleClientModel> resource =null;
		if(client.getLink()!=null)
			resource = new Resource<EligibleClientModel>(client,new Link(client.getLink()).withRel("client")); 
		else
			resource = new Resource<EligibleClientModel>(client);
		return new ResponseEntity<Resource>(resource,HttpStatus.OK);
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
			@Validated @RequestBody EligibleClientModel eligibleClientModel) throws Exception {
		ResponseEntity<String> responseEntity = null;
	
			boolean status = eligibleClientService.updateEligibleClient(UUID.fromString(id), eligibleClientModel);
			responseEntity = ResponseEntity.ok("{\"updated\": \""+ status +"\"}\"");
		
		return responseEntity;
	}
	
	
	@RequestMapping(value = "/{clientId}/history", method = RequestMethod.GET)
	@APIMapping(value="create-eligible-client")
	public EligibleClientAuditsModel getEligibleClientsAudit(@PathVariable("clientId") UUID clientId,HttpServletRequest request) {
		Session session = sessionHelper.getSession(request);

		String projectGroupCode = session.getAccount().getProjectGroup().getProjectGroupCode();

		return eligibleClientService.getEligibleClientsAudit(clientId,projectGroupCode);
	}
}
