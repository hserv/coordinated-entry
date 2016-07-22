package com.hserv.coordinatedentry.housinginventory.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class DatabaseConfiguration {

/*	@Value("${spring.datasource.driverClassName}")
	private String databaseDriverClassName;

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.datasource.username}")
	private String databaseUsername;

	@Value("${spring.datasource.password}")
	private String databasePassword;
	
	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;
	*/
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		 	LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		 		sessionFactoryBean.setDataSource(relationalDataSource());
		 		sessionFactoryBean.setPackagesToScan("com.hserv.coordinatedentry.housinginventory.domain");
				Properties hibernateProperties = new Properties();
				hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
				hibernateProperties.put("hibernate.show_sql", true);
				hibernateProperties.put("hibernate.temp.use_jdbc_metadata_defaults","false");
				sessionFactoryBean.setHibernateProperties(hibernateProperties);
		
				return sessionFactoryBean;
	}

    @Bean
    public BasicDataSource relationalDataSource(){
    	BasicDataSource datasource = new BasicDataSource();
    	datasource.setDriverClassName("org.postgresql.Driver");
    	datasource.setUrl("jdbc:postgresql://hmis-multischema-db.ct16elltavnx.us-west-2.rds.amazonaws.com:5432/hmis");
    	datasource.setUsername("hmisdb1");
    	datasource.setPassword("hmisdb1234");
    	return datasource;
    }
    


	/*@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}*/
    

	@Bean
	@Autowired
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}
	
}
