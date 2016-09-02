package com.hserv.coordinatedentry.housinginventory.enums;

import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

public enum HousingstatusEnum {

	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), NINTY_NINE(99);

	private final Integer status;

	HousingstatusEnum(final Integer state) {
		this.status = state;
	}

	public int getValue() {
		return this.status;
	}

	private static Map<Integer, HousingstatusEnum> namesMap = new HashMap<Integer, HousingstatusEnum>();

	static {

		namesMap.put(1, ONE);
		namesMap.put(2, TWO);
		namesMap.put(3, THREE);
		namesMap.put(4, FOUR);
		namesMap.put(8, EIGHT);
		namesMap.put(9, NINE);
		namesMap.put(99, NINTY_NINE);
	}

	@JsonCreator
	public static HousingstatusEnum forValue(Integer value) {
		return namesMap.get(value);
	}

	@JsonValue
	public Integer toValue() {
		for (Entry<Integer, HousingstatusEnum> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}
}