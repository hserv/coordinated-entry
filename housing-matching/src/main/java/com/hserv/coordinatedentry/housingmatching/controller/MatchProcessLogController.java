package com.hserv.coordinatedentry.housingmatching.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.entity.MatchProcessLogEntity;
import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.MatchProcessLog;
import com.hserv.coordinatedentry.housingmatching.service.MatchProcessLogService;
import com.servinglynk.hmis.warehouse.core.model.exception.MissingParameterException;

@RestController
@RequestMapping("/matchprocesslog")
public class MatchProcessLogController extends BaseController {
	
	
	@Autowired
	MatchProcessLogService matchProcessLogService;
	@Autowired
	private PagedResourcesAssembler assembler;
	
	private ResourceAssembler<MatchProcessLogEntity, Resource<MatchProcessLog>> matchProcessLogAssembler = new MatchProcessLogController.MatchProcessLogAssembler();
	
	private class MatchProcessLogAssembler implements ResourceAssembler<MatchProcessLogEntity, Resource<MatchProcessLog>> {

		@Override
		public Resource<MatchProcessLog> toResource(MatchProcessLogEntity arg0) {
			MatchProcessLog log = new MatchProcessLog();
			BeanUtils.copyProperties(arg0, log);
			log.setProcessDate(arg0.getDateCreated());
			log.setStatusMessage(arg0.getStatusMessage());
			Resource<MatchProcessLog> resource = new Resource<MatchProcessLog>(log);
			return resource;
		}
	}	


	@RequestMapping(method = RequestMethod.GET)
	@APIMapping(value="get-eligible-clients")
	public ResponseEntity<Resources<Resource>> getEligibleClients(Pageable pageable,HttpServletRequest request,
			@RequestParam(name="processid",required=false) UUID processId,
			@RequestParam(name="clientid",required=false) UUID clientId, 
			@RequestParam(name="projectid",required=false) UUID projectId,
			@RequestParam(name="housingunitid",required=false) UUID housingUnitId,
			@RequestParam(name="matchid",required= false) UUID matchId
			) throws Exception {
		
		if(clientId==null && projectId==null && housingUnitId==null && processId==null && matchId ==null)
			 throw new MissingParameterException(" ProcessId or HousingUnitId or ProjectId or ClientId or MatchId required.");
		
		Page<MatchProcessLogEntity> entities = matchProcessLogService.getMatchProcessLog(processId,housingUnitId,projectId,clientId,matchId, pageable);
		return new ResponseEntity<>(assembler.toResource(entities,matchProcessLogAssembler),
				HttpStatus.OK);
	}

}