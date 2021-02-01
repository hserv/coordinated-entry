package com.servinglynk.hmis.warehouse.enums;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;


public class SurveyCategoryEnumType extends GenericEnumType<String, SurveyCategoryEnum> {
	public SurveyCategoryEnumType() throws NoSuchMethodException, InvocationTargetException,
			IllegalAccessException {
		super(SurveyCategoryEnum.class, SurveyCategoryEnum.values(), "getValue", Types.OTHER);
	}

	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}