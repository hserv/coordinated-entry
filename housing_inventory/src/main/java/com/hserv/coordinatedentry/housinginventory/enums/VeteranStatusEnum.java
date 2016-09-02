package com.hserv.coordinatedentry.housinginventory.enums;

import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

public enum VeteranStatusEnum {

	ZERO(0), ONE(1), EIGHT(8), NINE(9), NINTY_NINE(99);

	private final Integer status;

	VeteranStatusEnum(final Integer state) {
		this.status = state;
	}

	public int getValue() {
		return this.status;
	}

	private static Map<Integer, VeteranStatusEnum> namesMap = new HashMap<Integer, VeteranStatusEnum>();

	static {

		namesMap.put(0, ZERO);
		namesMap.put(1, ONE);
		namesMap.put(8, EIGHT);
		namesMap.put(9, NINE);
		namesMap.put(99, NINTY_NINE);
	}

	@JsonCreator
	public static VeteranStatusEnum forValue(Integer value) {
		return namesMap.get(value);
	}

	@JsonValue
	public Integer toValue() {
		for (Entry<Integer, VeteranStatusEnum> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}
}
