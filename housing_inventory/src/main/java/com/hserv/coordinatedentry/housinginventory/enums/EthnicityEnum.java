package com.hserv.coordinatedentry.housinginventory.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EthnicityEnum {

	ZERO(0), ONE(1), EIGHT(8), NINE(9), NINTY_NINE(99);

	private final Integer status;

	EthnicityEnum(final Integer state) {
		this.status = state;
	}

	public int getValue() {
		return this.status;
	}

	private static Map<Integer, EthnicityEnum> namesMap = new HashMap<Integer, EthnicityEnum>();

	static {
		namesMap.put(0, ZERO);
		namesMap.put(1, ONE);
		namesMap.put(8, EIGHT);
		namesMap.put(9, NINE);
		namesMap.put(99, NINTY_NINE);
	}

	@JsonCreator
	public static EthnicityEnum forValue(Integer value) {
		return namesMap.get(value);
	}

	@JsonValue
	public Integer toValue() {
		for (Entry<Integer, EthnicityEnum> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}
}
