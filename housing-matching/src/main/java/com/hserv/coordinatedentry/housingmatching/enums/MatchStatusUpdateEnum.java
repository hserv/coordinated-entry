package com.hserv.coordinatedentry.housingmatching.enums;

import java.util.HashMap;
import java.util.Map;

public enum MatchStatusUpdateEnum {

	REFERRED_TO_AGENCY("1"),

	AGENCY_SETS_APPT_WITH_CLIENT("3"),
	PAPER_WORK_RECEIVED("4"),

	MOVE_ARRANGED_FOR_CLIENT("6"),
	CLIENT_MOVE_COMPLETE("7"),
	AGENCY_RECEIVED_INFO("5"),	
	AGENCY_CONTACTED_CLIENT("2"),
	CLIENT_REJECTED("10");
	
	private final String status;
		
	
	MatchStatusUpdateEnum(final String state) {
		this.status = state;
	}
	
	private static Map<String, MatchStatusUpdateEnum> valueMap = new HashMap<String, MatchStatusUpdateEnum>();

    static {
        for (MatchStatusUpdateEnum unit : values()) {
            valueMap.put(unit.getValue(), unit);
        }
    }
    

	public String getValue() {
		return this.status;
	}

	public static MatchStatusUpdateEnum lookupEnum(String value) {
        return MatchStatusUpdateEnum.valueMap.get(value);
    }
	
}