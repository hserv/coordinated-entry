package com.servinglynk.hmis.warehouse.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.servinglynk.hmis.warehouse.core.model.ParamResponse;

public enum HmisVersionEnum {
	/** Enum Constant. */
	ONE("4.01","v2014"),
	/** Enum Constant. */
	TWO("4.11","v2015"),
	/** Enum Constant. */
	THREE("5.1","v2016"),
	/** Enum Constant. */
	FOUR("6.12","v2017"),
	/** Enum Constant. */
	FIVE("FY2020","v2020");
	/**
	 * Internal storage of status field value, see the Enum spec for
 	 * clarification.
 	 */
	private final String status;
	private final String description;
	
	/**
	 * Enum constructor for ActiveState.
	 * @param state Value.
	 */
	HmisVersionEnum(final String state,final String description) {
		this.status = state;
		this.description = description;
	}
	
	/** Construct a map for reverse lookup. */
	private static Map<String, HmisVersionEnum> valueMap = new HashMap<String, HmisVersionEnum>();

    static {
    	// construct hashmap for later possible use.
        for (HmisVersionEnum unit : values()) {
            valueMap.put(unit.getValue(), unit);
        }
    }
    
	/**
	 * Current string value stored in the enum.
	 * 
	 * @return string value.
	 */
	public String getValue() {
		return this.status;
	}	
	/***
	 * description
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
     * Perform a reverse lookup (given a value, obtain the enum).
     * 
     * @param value to search
     * @return Enum object.
     */
    public static HmisVersionEnum lookupEnum(String value) {
        return HmisVersionEnum.valueMap.get(value);
    }
    /***
     * Get PickList values for the APIs
     * @return
     */
    public static List<ParamResponse> getPickList() {
    	// construct hashmap for later possible use.
    	List<ParamResponse> responses = new ArrayList();
        for (HmisVersionEnum unit : values()) {
        	responses.add(new ParamResponse(unit.getValue(), unit.getDescription()));
        }
        return responses;
    }
}
