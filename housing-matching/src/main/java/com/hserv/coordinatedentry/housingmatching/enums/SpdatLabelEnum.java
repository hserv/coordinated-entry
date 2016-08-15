package com.hserv.coordinatedentry.housingmatching.enums;
import java.util.Map;
import java.util.HashMap;
/**
 * Defines the DisabilitiesDisabilitytypeEnum enumeration.
 * 
 * @author Sandeep Dolia
 *
 */
public enum SpdatLabelEnum {

	/** Enum Constant. */
	YOUTH("YOUTH"),
	/** Enum Constant. */
	SINGLE_ADULT("SINGLE_ADULT"),
	/** Enum Constant. */
	FAMILY("FAMILY");

	/**
	 * Internal storage of status field value, see the Enum spec for
 	 * clarification.
 	 */
	private final String status;
	
	/**
	 * Enum constructor for ActiveState.
	 * @param state Value.
	 */
	SpdatLabelEnum(final String state) {
		this.status = state;
	}
	
	/** Construct a map for reverse lookup. */
	private static Map<String, SpdatLabelEnum> valueMap = new HashMap<String, SpdatLabelEnum>();

    static {
    	// construct hashmap for later possible use.
        for (SpdatLabelEnum unit : values()) {
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

	/**
     * Perform a reverse lookup (given a value, obtain the enum).
     * 
     * @param value to search
     * @return Enum object.
     */
    public static SpdatLabelEnum lookupEnum(String value) {
        return SpdatLabelEnum.valueMap.get(value);
    }

}
