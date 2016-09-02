package com.hserv.coordinatedentry.housinginventory.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DatabaseConfiguration {
	
	@Autowired
	Environment env;
	
    @Bean
    public BasicDataSource relationalDataSource(){
    	BasicDataSource datasource = new BasicDataSource();
    	datasource.setDriverClassName("org.postgresql.Driver");
    	datasource.setUrl("jdbc:postgresql://hmis-multischema-db.ct16elltavnx.us-west-2.rds.amazonaws.com:5432/hmis");
    	datasource.setUsername("hmisdb1");
    	datasource.setPassword("hmisdb1234");
    	return datasource;
    }	
}
