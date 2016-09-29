package com.hserv.coordinatedentry.housingmatching.service.community.sanjose;

import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;

@Service
public class SanJoseCommunityStrategy implements MatchStrategy{

	@Override
	public String getProgramType(int spdatScore,boolean individual,boolean family) {
		return null;
	}
	
	@Override
	public int getAdditionalScore(boolean individual, boolean family, int age, boolean familyWithChildren,
			boolean vet, boolean frailHealth) {
		return 0;
	}

	@Override
	public String getProgramType(int spdatScore, String spdatLabel) {
		return null;
	}

	@Override
	public int getAdditionalScore(int age, String spdatLabel) {
		return 0;
	}
}
