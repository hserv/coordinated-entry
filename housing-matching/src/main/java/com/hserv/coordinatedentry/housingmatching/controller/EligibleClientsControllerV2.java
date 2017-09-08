package com.hserv.coordinatedentry.housingmatching.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.ClientModel;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.translator.EligibleClientsTranslator;
import com.servinglynk.hmis.warehouse.core.model.Session;

@RestController
@RequestMapping(value = "/v2/eligibleclients", produces = "application/json")
public class EligibleClientsControllerV2 extends BaseController {
	
	

	@Autowired
	EligibleClientService eligibleClientService;
	
	
	@Autowired
	private PagedResourcesAssembler assembler;
	
	@Autowired
	private EligibleClientsTranslator eligibleClientsTranslator;
	
	
	
	private ResourceAssembler<EligibleClient, Resource<EligibleClientModel>> eligibleClientsAssembler = new EligibleClientsControllerV2.EligibleClientsAssembler();
	
	private class EligibleClientsAssembler implements ResourceAssembler<EligibleClient, Resource<EligibleClientModel>> {

		@Override
		public Resource<EligibleClientModel> toResource(EligibleClient arg0) {
			EligibleClientModel model = eligibleClientsTranslator.translate(arg0);
			if(arg0.getClient()!=null) {
				ClientModel clientModel = new ClientModel();
				clientModel.setDob(Date.from( arg0.getClient().getDob().atZone(ZoneId.systemDefault()).toInstant()));
				clientModel.setEmailAddress(arg0.getClient().getEmailAddress());
				clientModel.setFirstName(arg0.getClient().getFirstName());
				clientModel.setLastName(arg0.getClient().getLastName());
				clientModel.setMiddleName(arg0.getClient().getMiddleName());
				clientModel.setPhoneNumber(arg0.getClient().getPhoneNumber());
				model.setClient(clientModel);
			}
			Resource<EligibleClientModel> resource = new Resource<EligibleClientModel>(model);
			if(arg0.getClientLink()!=null)
				resource.add(new Link(arg0.getClientLink()).withRel("client"));
			return resource;
		}
	}

	
	
	@RequestMapping(method = RequestMethod.GET)
	@APIMapping(value = "get-eligible-clients")
	public ResponseEntity<Resources<Resource>> getEligibleClientsv2(Pageable pageable, HttpServletRequest request,
			@RequestParam(defaultValue = "active", required = false) String filter) throws Exception {
		Session session = sessionHelper.getSession(request);
		String projectGroupCode = session.getAccount().getProjectGroup().getProjectGroupCode();
		Link statusLink = linkTo(methodOn(BatchProcessController.class).getCurrentStatus(request))
				.withRel("scoreprocessingstatus");
		PageRequest page = new PageRequest(0, 20);
		Link historyLink = linkTo(methodOn(BatchProcessController.class).getBatchProcessHistory(page, request))
				.withRel("scoreprocessinghistory");

		PagedResources resources = assembler.toResource(
				eligibleClientService.getEligibleClients(projectGroupCode, pageable, filter), eligibleClientsAssembler);
		resources.getLinks().add(statusLink);
		resources.getLinks().add(historyLink);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@APIMapping(value="get-eligible-client-by-id")
	public ResponseEntity<Resource>  getEligibleClientById(@PathVariable UUID id) {
		EligibleClientModel client =  eligibleClientService.getEligibleClientDetail(id,"v2");
		Resource<EligibleClientModel> resource =null;
		if(client.getLink()!=null)
			resource = new Resource<EligibleClientModel>(client,new Link(client.getLink()).withRel("client")); 
		else
			resource = new Resource<EligibleClientModel>(client);
		return new ResponseEntity<Resource>(resource,HttpStatus.OK);
	}




}
