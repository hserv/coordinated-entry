package com.hserv.coordinatedentry.housingmatching.model;

public enum CommunityType {
	MONTEREY("Monterey"), SAN_JOSE("SanJose");

	private String value;

	private CommunityType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
