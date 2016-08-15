package com.hserv.coordinatedentry.housingmatching.enums;
import java.util.Map;
import java.util.HashMap;
/**
 * Defines the DisabilitiesDisabilitytypeEnum enumeration.
 * 
 * @author Sandeep Dolia
 *
 */
public enum MatchStatusEnum {

	/** Enum Constant. */
	ACCEPTED("ACCEPTED"),
	/** Enum Constant. */
	REJECTED("REJECTED");

	/**
	 * Internal storage of status field value, see the Enum spec for
 	 * clarification.
 	 */
	private final String status;
	
	/**
	 * Enum constructor for ActiveState.
	 * @param state Value.
	 */
	MatchStatusEnum(final String state) {
		this.status = state;
	}
	
	/** Construct a map for reverse lookup. */
	private static Map<String, MatchStatusEnum> valueMap = new HashMap<String, MatchStatusEnum>();

    static {
    	// construct hashmap for later possible use.
        for (MatchStatusEnum unit : values()) {
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
    public static MatchStatusEnum lookupEnum(String value) {
        return MatchStatusEnum.valueMap.get(value);
    }

}
