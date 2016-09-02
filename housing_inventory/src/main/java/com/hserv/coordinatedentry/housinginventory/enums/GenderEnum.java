package com.hserv.coordinatedentry.housinginventory.enums;

import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;


public enum GenderEnum {
	ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), EIGHT(8), NINE(9), NINTY_NINE(9);

	private final Integer status;

	GenderEnum(final Integer state) {
		this.status = state;
	}

	public int getValue() {
		return this.status;
	}

	private static Map<Integer, GenderEnum> namesMap = new HashMap<Integer, GenderEnum>();

	static {
		namesMap.put(0, ZERO);
		namesMap.put(1, ONE);
		namesMap.put(2, TWO);
		namesMap.put(3, THREE);
		namesMap.put(4, FOUR);
		namesMap.put(8, EIGHT);
		namesMap.put(9, NINE);
		namesMap.put(99, NINTY_NINE);
	}

	@JsonCreator
	public static GenderEnum forValue(Integer value) {
		return namesMap.get(value);
	}

	@JsonValue
	public Integer toValue() {
		for (Entry<Integer, GenderEnum> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}
}