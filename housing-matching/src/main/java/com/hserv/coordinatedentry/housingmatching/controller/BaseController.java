package com.hserv.coordinatedentry.housingmatching.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.hserv.coordinatedentry.housingmatching.helper.SessionHelper;

public class BaseController {

	@Autowired
	SessionHelper sessionHelper;
	
}
