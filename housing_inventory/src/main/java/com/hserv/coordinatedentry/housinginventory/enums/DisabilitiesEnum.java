package com.hserv.coordinatedentry.housinginventory.enums;
import java.util.Map;
import java.util.HashMap;
/**
 * Defines the DisabilitiesDisabilitytypeEnum enumeration.
 * 
 * @author Sandeep Dolia
 *
 */
public enum DisabilitiesEnum {

	/** Enum Constant. */
	FIVE("5"),
	/** Enum Constant. */
	SIX("6"),
	/** Enum Constant. */
	SEVEN("7"),
	/** Enum Constant. */
	EIGHT("8"),
	/** Enum Constant. */
	NINE("9"),
	/** Enum Constant. */
	TEN("10");
	/**
	 * Internal storage of status field value, see the Enum spec for
 	 * clarification.
 	 */
	private final String status;
	
	/**
	 * Enum constructor for ActiveState.
	 * @param state Value.
	 */
	DisabilitiesEnum(final String state) {
		this.status = state;
	}
	
	/** Construct a map for reverse lookup. */
	private static Map<String, DisabilitiesEnum> valueMap = new HashMap<String, DisabilitiesEnum>();

    static {
    	// construct hashmap for later possible use.
        for (DisabilitiesEnum unit : values()) {
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
    public static DisabilitiesEnum lookupEnum(String value) {
        return DisabilitiesEnum.valueMap.get(value);
    }

}
