package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.dao.PropertyDao;
import com.hserv.coordinatedentry.housingmatching.entity.PropertyEntity;



public class PropertyReaderServiceImpl {

	@Autowired
	PropertyDao propertyDao;
	
	@Autowired
	Environment env;

	@Transactional
	public String readSharingRuleProperty() {
			PropertyEntity propertyEntity =propertyDao.readSharingRuleProperty();
			if(propertyEntity==null)
								return null;
			else
			return	propertyEntity.getValue();
	}
	
	
	private final static Logger logger = Logger.getLogger(PropertyReaderServiceImpl.class);

	private Properties properties = new Properties();
	
	@Autowired
    private DataSource dataSource;
	
	private String table="base.hmis_property";
	private String nameColumn="";
	private String keyColumn="KEY_NAME";
	private String valueColumn="VALUE";
	private String name="";

	public Properties getProperties(String serviceName) {
		load("common".toUpperCase());
		load(serviceName);
		System.out.println(properties.size()+" properties loaded");
		return properties;
	}
		
	private void load(String serviceName) {
		StringBuilder query = new StringBuilder("SELECT SERVICE, KEY_NAME, VALUE FROM ");
		query.append(table);
			query.append(" WHERE ");
			query.append(" SERVICE =?"); 

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("properties query "+query.toString());
		try {
			conn = dataSource.getConnection();

			// bind the parameters
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1,serviceName);
			rs = pstmt.executeQuery();

			//
			while (rs.next()) {
				String key = rs.getString(keyColumn);
				String value = rs.getString(valueColumn);
				if (value!=null){
					properties.put(key, value);
				}else{
					properties.put(key,"");
				}
			}
		} catch (SQLException exc) {
			logger.error("Failed to initialize property cache for table[" + table + "] service [" + serviceName + "] ", exc);
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception exc) {
				logger.error("Failed to close rs, pstmt, conn!", exc);
			}
		}
		
		logger.info("Initializing property cache for table[" + table + "] service [" + serviceName + "] .........................END");
	}


}
