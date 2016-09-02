package com.hserv.coordinatedentry.housinginventory.enums;

import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

public enum ResidencepriorEnum {

	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TWELVE(12), THIRTEEN(13), FOURTEEN(
			14), FIFTEEN(15), SIXTEEN(16), SEVENTEEN(17), EIGHTEEN(18), NINTEEN(19), TWENTY(
					20), TWENTY_ONE(21), TWENTY_TWO(
							22), TWENTY_THREE(23), TWENTY_FOUR(24), TWENTY_FIVE(25), TWENTY_SIX(26), NINTY_NINE(99);

	private final Integer status;

	ResidencepriorEnum(final Integer state) {
		this.status = state;
	}

	public int getValue() {
		return this.status;
	}

	private static Map<Integer, ResidencepriorEnum> namesMap = new HashMap<Integer, ResidencepriorEnum>();

	static {

		namesMap.put(1, ONE);
		namesMap.put(2, TWO);
		namesMap.put(3, THREE);
		namesMap.put(4, FOUR);
		namesMap.put(5, FIVE);
		namesMap.put(6, SIX);
		namesMap.put(7, SEVEN);
		namesMap.put(8, EIGHT);
		namesMap.put(9, NINE);
		namesMap.put(12, TWELVE);
		namesMap.put(13, THIRTEEN);
		namesMap.put(14, FOURTEEN);
		namesMap.put(15, FIFTEEN);
		namesMap.put(16, SIXTEEN);
		namesMap.put(17, SEVENTEEN);
		namesMap.put(18, EIGHTEEN);
		namesMap.put(19, NINTEEN);
		namesMap.put(20, TWENTY);
		namesMap.put(21, TWENTY_ONE);
		namesMap.put(22, TWENTY_TWO);
		namesMap.put(23, TWENTY_THREE);
		namesMap.put(24, TWENTY_FOUR);
		namesMap.put(25, TWENTY_FIVE);
		namesMap.put(26, TWENTY_SIX);
		namesMap.put(99, NINTY_NINE);

	}

	@JsonCreator
	public static ResidencepriorEnum forValue(Integer value) {
		return namesMap.get(value);
	}

	@JsonValue
	public Integer toValue() {
		for (Entry<Integer, ResidencepriorEnum> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}
}