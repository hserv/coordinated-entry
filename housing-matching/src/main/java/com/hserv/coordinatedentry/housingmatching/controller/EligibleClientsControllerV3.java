
package com.hserv.coordinatedentry.housingmatching.controller;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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

import com.hserv.coordinatedentry.housingmatching.dao.ClientRepository;
import com.hserv.coordinatedentry.housingmatching.entity.Client;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.ClientModel;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientsModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientServiceV3;
import com.hserv.coordinatedentry.housingmatching.translator.EligibleClientsTranslator;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;
import com.servinglynk.hmis.warehouse.core.web.interceptor.TrustedAppHelper;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Controller for eligible-clients.
 * 
 */
@RestController
@RequestMapping(value = "/v3/eligibleclients", produces = "application/json")
public class EligibleClientsControllerV3 extends BaseController {

	@Autowired
	EligibleClientServiceV3 eligibleClientServiceV3;
	
	
	@Autowired
	EligibleClientService eligibleClientService;
	
	
	 @Autowired
	 SessionHelper sessionHelper;
	 
	 @Autowired
	 TrustedAppHelper trustedAppHelper;
	
	
	@Autowired
	private PagedResourcesAssembler assembler;
	
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	private EligibleClientsTranslator eligibleClientsTranslator;
	

	private ResourceAssembler<EligibleClient, Resource<EligibleClientModel>> eligibleClientsAssembler = new EligibleClientsControllerV3.EligibleClientsAssembler();
	
	private class EligibleClientsAssembler implements ResourceAssembler<EligibleClient, Resource<EligibleClientModel>> {

		@Override
		public Resource<EligibleClientModel> toResource(EligibleClient arg0) {
			EligibleClientModel model = eligibleClientsTranslator.translateV2(arg0);
			/*
			 * if(arg0.getClient()!=null) { ClientModel clientModel = new ClientModel();
			 * 
			 * String projectGroup = SecurityContextUtil.getUserProjectGroup(); List<Client>
			 * clients =
			 * clientRepository.findByDedupClientIdAndProjectGroupCodeOrderBySchemaYearDesc(
			 * arg0.getClientDedupId(), projectGroup); Client client = arg0.getClient();
			 * if(!clients.isEmpty()) client = clients.get(0); if(client.getDob()!=null)
			 * clientModel.setDob(Date.from(
			 * client.getDob().atZone(ZoneId.systemDefault()).toInstant()));
			 * clientModel.setEmailAddress(client.getEmailAddress());
			 * clientModel.setFirstName(client.getFirstName());
			 * clientModel.setLastName(client.getLastName());
			 * clientModel.setMiddleName(client.getMiddleName());
			 * clientModel.setPhoneNumber(client.getPhoneNumber());
			 * clientModel.setId(client.getId()); model.setClient(clientModel); }
			 */
			Resource<EligibleClientModel> resource = new Resource<EligibleClientModel>(model);
			if(arg0.getClient()!=null)
				resource.add(new Link("/hmis-clientapi/rest/"+arg0.getClient().getSchemaYear()+"/clients/"+arg0.getClient().getId()).withRel("client"));
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

		PagedResources resources = assembler.toResource(eligibleClientServiceV3.getEligibleClients(projectGroupCode,pageable,filter), eligibleClientsAssembler);
		resources.getLinks().add(statusLink);
		resources.getLinks().add(historyLink);
		return new ResponseEntity<>(resources,
				HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.POST)
	@APIMapping(value="create-eligible-client")
	public ResponseEntity<String> createEligibleClient(
			 @RequestBody EligibleClientModel eligibleClientModel,HttpServletRequest request) throws Exception {
		EligibleClientModel client =  eligibleClientServiceV3.getEligibleClientDetail(eligibleClientModel.getClientDedupId(),"v2");
		eligibleClientModel.setClientId(client.getClientId());
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status = eligibleClientService.createEligibleClient(eligibleClientModel);
			responseEntity = ResponseEntity.ok("{\"added\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	

	@RequestMapping(method = RequestMethod.PUT,value = "/{dedupClientId}")
	@APIMapping(value="create-eligible-client")
	public ResponseEntity<String> updateEligibleClient(@PathVariable UUID dedupClientId,
			 @RequestBody EligibleClientModel eligibleClientModel,HttpServletRequest request) throws Exception {		
		EligibleClientModel client =  eligibleClientServiceV3.getEligibleClientDetail(dedupClientId,"v2");
		ResponseEntity<String> responseEntity = null;
		try {
			eligibleClientModel.setClientId(client.getClientId());
			eligibleClientModel.setClientDedupId(client.getClientDedupId());
			boolean status = eligibleClientService.updateEligibleClient(client.getClientId(),eligibleClientModel);
			responseEntity = ResponseEntity.ok("{\"added\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			ex.printStackTrace();
			responseEntity = new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/{dedupClientId}", method = RequestMethod.GET)
	@APIMapping(value="get-eligible-client-by-id")
	public ResponseEntity<Resource>  getEligibleClientByDedupId(@PathVariable UUID dedupClientId) {
		EligibleClientModel client =  eligibleClientServiceV3.getEligibleClientDetail(dedupClientId,"v2");
		Resource<EligibleClientModel> resource =null;
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		if(client.getClient()!=null)
			resource = new Resource<EligibleClientModel>(client,new Link("/hmis-clientapi/rest/"+client.getClient().getSchemaYear()+"/clients/"+client.getClient().getId()).withRel("client")); 
		else
			resource = new Resource<EligibleClientModel>(client);
		return new ResponseEntity<Resource>(resource,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{dedupClientId}", method = RequestMethod.DELETE)
	@APIMapping(value="get-eligible-client-by-id")
	public ResponseEntity<String>  deleteEligibleClientByDedupId(@PathVariable UUID dedupClientId) {
		ResponseEntity<String> responseEntity = null;
		try {
			boolean status =  eligibleClientServiceV3.deleteEligibleClientByDedupId(dedupClientId);
			responseEntity = ResponseEntity.ok("{\"deleted\": \""+ status +"\"}\"");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;

	}

}
