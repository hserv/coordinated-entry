package com.servinglynk.hmis.warehouse.util;

import java.util.HashMap;
import java.util.Map;

public enum QuestionTypeEnum {

	TEXT("TEXT"),
	CHECKBOX("CHECKBOX"),
	REDIO("REDIO"),
	DROPDOWN("DROPDOWN");
	
	
	QuestionTypeEnum(final String state) {
		this.status = state;
	}
	
	private final String status;
	
	public String getValue() {
		return this.status;
	}
	
	private static Map<String, QuestionTypeEnum> valueMap = new HashMap<String, QuestionTypeEnum>();
	private static Map<String, QuestionTypeEnum> pickListMap = new HashMap<String, QuestionTypeEnum>();
	
	static {
        for (QuestionTypeEnum unit : values()) {
            valueMap.put(unit.getValue(), unit);
        }
        pickListMap.put(QuestionTypeEnum.CHECKBOX.getValue(), QuestionTypeEnum.CHECKBOX);
        pickListMap.put(QuestionTypeEnum.REDIO.getValue(), QuestionTypeEnum.REDIO);
        pickListMap.put(QuestionTypeEnum.DROPDOWN.getValue(), QuestionTypeEnum.DROPDOWN);
        
    }
	
	public static QuestionTypeEnum lookupEnum(String value) {
        return QuestionTypeEnum.valueMap.get(value);
    }
	
	public static boolean isPickListType(String value){
		return QuestionTypeEnum.pickListMap.containsKey(value);
	}
	
}