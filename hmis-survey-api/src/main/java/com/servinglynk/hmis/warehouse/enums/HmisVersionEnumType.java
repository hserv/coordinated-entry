package com.servinglynk.hmis.warehouse.enums;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;


public class HmisVersionEnumType extends GenericEnumType<String, HmisVersionEnum> {
	public HmisVersionEnumType() throws NoSuchMethodException, InvocationTargetException,
			IllegalAccessException {
		super(HmisVersionEnum.class, HmisVersionEnum.values(), "getValue", Types.OTHER);
	}

	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}