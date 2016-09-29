package com.hserv.coordinatedentry.housingmatching.service;

public interface MatchStrategy {
	
	public String getProgramType(int spdatScore,boolean individual,boolean family);
	
	String getProgramType(int spdatScore,String spdatLabel);
	
	int getAdditionalScore(int age,String spdatLabel);
	
	public int getAdditionalScore(boolean individual, boolean family, int age, boolean familyWithChildren,
			boolean vet, boolean frailHealth);

}

