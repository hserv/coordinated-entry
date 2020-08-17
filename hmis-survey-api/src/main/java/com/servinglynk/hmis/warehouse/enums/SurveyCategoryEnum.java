package com.servinglynk.hmis.warehouse.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.servinglynk.hmis.warehouse.core.model.ParamResponse;

public enum SurveyCategoryEnum {
	/** Enum Constant. */
	ONE("1","Project entry"),
	/** Enum Constant. */
	TWO("2","Project update"),
	/** Enum Constant. */
	THREE("3","Project exit"),
	/** Enum Constant. */
	FIVE("3","Project annual assessment");
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
	SurveyCategoryEnum(final String state,final String description) {
		this.status = state;
		this.description = description;
	}
	
	/** Construct a map for reverse lookup. */
	private static Map<String, SurveyCategoryEnum> valueMap = new HashMap<String, SurveyCategoryEnum>();

    static {
    	// construct hashmap for later possible use.
        for (SurveyCategoryEnum unit : values()) {
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
    public static SurveyCategoryEnum lookupEnum(String value) {
        return SurveyCategoryEnum.valueMap.get(value);
    }
    /***
     * Get PickList values for the APIs
     * @return
     */
    public static List<ParamResponse> getPickList() {
    	// construct hashmap for later possible use.
    	List<ParamResponse> responses = new ArrayList();
        for (SurveyCategoryEnum unit : values()) {
        	responses.add(new ParamResponse(unit.getValue(), unit.getDescription()));
        }
        return responses;
    }
}
