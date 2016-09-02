package com.hserv.coordinatedentry.housinginventory.enums;

import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

/**
 * Defines the EnrollmentMonthshomelesspastthreeyearsEnum enumeration.
 * 
 * @author Sandeep Dolia
 *
 */
public enum MonthshomelesspastthreeyearsEnum {

	HUNDRED(100), SEVEN(7), EIGHT(8), NINE(9), ONE_HUNDRED_ONE(101), ONE_HUNDRED_TWO(102), ONE_HUNDRED_THREE(
			103), ONE_HUNDRED_FOUR(104), ONE_HUNDRED_FIVE(105), ONE_HUNDRED_SIX(106), ONE_HUNDRED_SEVEN(
					107), ONE_HUNDRED_EIGHT(108), ONE_HUNDRED_NINE(109), ONE_HUNDRED_TEN(
							110), ONE_HUNDRED_TWELVE(112), ONE_HUNDRED_ELEVEN(111), NINTY_NINE(99);

	private final Integer status;

	MonthshomelesspastthreeyearsEnum(final Integer state) {
		this.status = state;
	}

	public int getValue() {
		return this.status;
	}

	private static Map<Integer, MonthshomelesspastthreeyearsEnum> namesMap = new HashMap<Integer, MonthshomelesspastthreeyearsEnum>();

	static {

		namesMap.put(100, HUNDRED);
		namesMap.put(7, SEVEN);
		namesMap.put(8, EIGHT);
		namesMap.put(9, NINE);
		namesMap.put(101, ONE_HUNDRED_ONE);
		namesMap.put(102, ONE_HUNDRED_TWO);
		namesMap.put(103, ONE_HUNDRED_THREE);
		namesMap.put(104, ONE_HUNDRED_FOUR);
		namesMap.put(105, ONE_HUNDRED_FIVE);
		namesMap.put(106, ONE_HUNDRED_SIX);
		namesMap.put(107, ONE_HUNDRED_SEVEN);
		namesMap.put(108, ONE_HUNDRED_EIGHT);
		namesMap.put(109, ONE_HUNDRED_NINE);
		namesMap.put(110, ONE_HUNDRED_TEN);
		namesMap.put(111, ONE_HUNDRED_TWELVE);
		namesMap.put(112, ONE_HUNDRED_ELEVEN);
		namesMap.put(99, NINTY_NINE);
	}

	@JsonCreator
	public static MonthshomelesspastthreeyearsEnum forValue(Integer value) {
		return namesMap.get(value);
	}

	@JsonValue
	public Integer toValue() {
		for (Entry<Integer, MonthshomelesspastthreeyearsEnum> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}
}