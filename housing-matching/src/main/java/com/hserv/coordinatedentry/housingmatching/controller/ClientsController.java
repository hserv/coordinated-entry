package com.hserv.coordinatedentry.housingmatching.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;


@RequestMapping("/clients")
@RestController
public class ClientsController extends BaseController {
	

	@Autowired
	MatchReservationsService matchReservationsService;
	
	@RequestMapping(value = "/{dedupId}/matches",method = RequestMethod.POST)
	@APIMapping(checkSessionToken = true, checkTrustedApp = true,value = "CREATE_MATCH")
	public MatchReservationModel manualMatch(@PathVariable("dedupId") UUID dedulpClient, @RequestBody MatchReservationModel matchReservationModel) throws Exception{
			return matchReservationsService.createManualMatch(dedulpClient,matchReservationModel);
	}
}
