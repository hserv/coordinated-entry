package com.hserv.coordinatedentry.housinginventory.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.housinginventory.repository.HousingInventoryRepository;
import com.hserv.coordinatedentry.housinginventory.annotation.APIMapping;

@RestController
@RequestMapping("/health")
public class HealthCheckResource extends BaseResource {

	@Autowired
	HousingInventoryRepository housingInventoryRepository;

	@APIMapping(value = "HEALTH_CHECK", checkSessionToken = true, checkTrustedApp = true)
	@RequestMapping(method = RequestMethod.GET)
	public void checkConnectionHealth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			housingInventoryRepository.validateConnection();
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}