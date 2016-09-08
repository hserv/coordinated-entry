package com.servinglynk.hmis.warehouse.util;

import java.util.HashMap;
import java.util.Map;

public enum QuestionDataTypeEnum {
   NUMBER("NUMBER"),
   BOOLEAN("BOOLEAN"),
   CHAR("CHAR");
	
	
	

	QuestionDataTypeEnum(final String state) {
		this.status = state;
	}
	
	private final String status;
	
	public String getValue() {
		return this.status;
	}
	
	private static Map<String, QuestionDataTypeEnum> valueMap = new HashMap<String, QuestionDataTypeEnum>();
	
	static {
        for (QuestionDataTypeEnum unit : values()) {
            valueMap.put(unit.getValue(), unit);
        }
    }
	
	public static QuestionDataTypeEnum lookupEnum(String value) {
        return QuestionDataTypeEnum.valueMap.get(value);
    }
}
