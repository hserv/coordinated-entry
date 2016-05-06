package com.hserv.coordinatedentry.housingmatching.external.impl;

import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.external.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Override
	public String getUserCommunity(String userId) {
		return "Monterey";
	}

}
