package com.hserv.coordinatedentry.housingmatching.model;

public enum ProgramType {
	PERMANENT_SUPPORTIVE_HOUSING("PSH"), TRANSITIONAL_HOUSING("TH"), RAPID_RE_HOUSING("RRH") , EMERGENCY_SHELTER("EmergencyShelter");
	private String value;

	private ProgramType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

}
