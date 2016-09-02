package com.hserv.coordinatedentry.housinginventory.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DisabilitiesEnum {

	FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

	private final Integer status;

	DisabilitiesEnum(final Integer state) {
		this.status = state;
	}

	public int getValue() {
		return this.status;
	}

	private static Map<Integer, DisabilitiesEnum> namesMap = new HashMap<Integer, DisabilitiesEnum>();

	static {
		namesMap.put(5, FIVE);
		namesMap.put(6, SIX);
		namesMap.put(7, SEVEN);
		namesMap.put(8, EIGHT);
		namesMap.put(9, NINE);
		namesMap.put(10, TEN);
	}

	@JsonCreator
	public static DisabilitiesEnum forValue(Integer value) {
		return namesMap.get(value);
	}

	@JsonValue
	public Integer toValue() {
		for (Entry<Integer, DisabilitiesEnum> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}
}
