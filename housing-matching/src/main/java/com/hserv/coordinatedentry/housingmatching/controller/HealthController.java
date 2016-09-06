package com.hserv.coordinatedentry.housingmatching.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.hserv.coordinatedentry.housingmatching.dao.MatchReservationsRepository;

@RestController
@RequestMapping("/health")
public class HealthController  extends BaseController {
	
	
	@Autowired
	MatchReservationsRepository repository;


	@com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping(value = "HEALTH_CHECK", checkSessionToken = true, checkTrustedApp = true)
	@RequestMapping(method = RequestMethod.GET)
	public void checkConnectionHealth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			repository.validateConnection();
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
