package com.hserv.coordinatedentry.housingmatching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.entity.MatchStatusRemarksEntity;
import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.MatchStatusRemarks;
import com.hserv.coordinatedentry.housingmatching.service.MatchStatusRemarksService;

@RestController
@RequestMapping(value = "/matchstatuses")
public class MatchStatusRemarksController extends BaseController {
	
	@Autowired
	MatchStatusRemarksService matchStatusRemarksService;
	
	@Autowired
	private PagedResourcesAssembler assembler;
	
	
	private ResourceAssembler<MatchStatusRemarksEntity, Resource<MatchStatusRemarks>> matchStatusRemarksAssembler = new MatchStatusRemarksController.MatchStatusRemarksAssembler();
	
	private class MatchStatusRemarksAssembler implements ResourceAssembler<MatchStatusRemarksEntity, Resource<MatchStatusRemarks>> {

		@Override
		public Resource<MatchStatusRemarks> toResource(MatchStatusRemarksEntity arg0) {
			MatchStatusRemarks remarks = new MatchStatusRemarks();
			remarks.setReason(arg0.getReason());
			remarks.setStatusCode(arg0.getStatusCode());
			Resource<MatchStatusRemarks> resource = new Resource<MatchStatusRemarks>(remarks);
			return resource;
		}
	}	
	
	@RequestMapping(method=RequestMethod.GET,value="/{statusCode}/remarks")
	@APIMapping(value="UPDATE_MATCH_STATUS")
	public ResponseEntity<Resources<Resource>> getStatusRemarks(
			@PathVariable("statusCode") Long statusCode,
			@PageableDefault(size=50)  Pageable pageable) throws Exception {
		return new ResponseEntity<>( assembler.toResource(matchStatusRemarksService.getRemarks(statusCode, pageable), matchStatusRemarksAssembler),
				HttpStatus.OK);
	}
}