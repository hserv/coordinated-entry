package com.hserv.coordinatedentry.housingmatching.service;

public interface MatchStrategy {
	
	public String getProgramType(int spdatScore,boolean individual,boolean family);
	
	public int getAdditionalScore(boolean individual, boolean family, int age, boolean familyWithChildren,
			boolean vet, boolean frailHealth);

}

