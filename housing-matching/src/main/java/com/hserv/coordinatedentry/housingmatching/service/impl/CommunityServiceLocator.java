package com.hserv.coordinatedentry.housingmatching.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.model.CommunityType;
import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;
import com.hserv.coordinatedentry.housingmatching.service.community.monterey.MontereyCommunityStrategy;
import com.hserv.coordinatedentry.housingmatching.service.community.sanjose.SanJoseCommunityStrategy;

@Component
public class CommunityServiceLocator {

	@Autowired
	MontereyCommunityStrategy montereyCommunityStrategy;

	@Autowired
	SanJoseCommunityStrategy sanJoseCommunityStrategy;

	public MatchStrategy locate(CommunityType communityType) {
		MatchStrategy matchStrategy = null;
		switch (communityType) {
		case MONTEREY:
			matchStrategy = montereyCommunityStrategy;
			break;
		case SAN_JOSE:
			matchStrategy = sanJoseCommunityStrategy;
			break;
		default:
			break;
		}
		return matchStrategy;
	}

}
