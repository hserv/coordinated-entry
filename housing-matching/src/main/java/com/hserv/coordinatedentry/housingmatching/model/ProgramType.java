package com.hserv.coordinatedentry.housingmatching.model;

public enum ProgramType {
	EMERGENCY_SHELTER("EmergencyShelter"),
	TRANSITIONAL_HOUSING("TH"),
	PERMANENT_SUPPORTIVE_HOUSING("PSH"),
	RAPID_RE_HOUSING("RRH") ;
	
	private String value;

	private ProgramType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

}
