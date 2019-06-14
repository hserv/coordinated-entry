package com.servinglynk.hmis.warehouse.util;

import java.time.LocalDateTime;

public class DateUtil {

	public static LocalDateTime least(LocalDateTime a, LocalDateTime b) {
	    return a == null ? b : (b == null ? a : (a.isBefore(b) ? a : b));
	}
}