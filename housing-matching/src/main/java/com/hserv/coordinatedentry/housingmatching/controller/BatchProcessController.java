package com.hserv.coordinatedentry.housingmatching.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.entity.BatchProcessEntity;
import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.BatchProcessModel;
import com.hserv.coordinatedentry.housingmatching.service.BatchProcessService;
import com.hserv.coordinatedentry.housingmatching.translator.BatchProcessTranslator;
import com.servinglynk.hmis.warehouse.core.model.Session;

@RestController
@RequestMapping("/scores")
public class BatchProcessController extends BaseController {

	@Autowired
	BatchProcessService batchProcessService;
	
	@Autowired
	BatchProcessTranslator batchProcessTranslator;
	
	@Autowired
	private PagedResourcesAssembler assembler;


	private ResourceAssembler<BatchProcessEntity, Resource<BatchProcessModel>> housingInventoryAssembler = new BatchProcessController.BatchStatusAssembler();
	
	private class BatchStatusAssembler implements ResourceAssembler<BatchProcessEntity, Resource<BatchProcessModel>> {

		@Override
		public Resource<BatchProcessModel> toResource(BatchProcessEntity arg0) {
			BatchProcessModel model = batchProcessTranslator.translate(arg0);
			return new Resource<BatchProcessModel>(model);
		}
	}	

	
	
	@RequestMapping(method=RequestMethod.GET,value="/status")
	@APIMapping(value="GET_BATCH_PROCESS_STATUS")
	public BatchProcessModel getCurrentStatus(HttpServletRequest request) throws Exception{
		Session session = sessionHelper.getSession(request);
		
		return batchProcessService.getScoreStatus(session.getAccount().getProjectGroup().getProjectGroupCode());
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/history")
	@APIMapping(value="GET_BATCH_PROCESS_HISTORY")
	public ResponseEntity<Resources<Resource>> getBatchProcessHistory(Pageable pageable,HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		return new ResponseEntity<>(assembler.toResource(batchProcessService.getScoreStatusHistory(session.getAccount().getProjectGroup().getProjectGroupCode(), pageable),housingInventoryAssembler),HttpStatus.OK);
	}
	
}