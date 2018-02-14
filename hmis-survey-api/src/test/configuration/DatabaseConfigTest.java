package configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.servinglynk.hmis.warehouse")
public class DatabaseConfigTest {
	

	@Primary
	@Bean
	public DataSource dataSource() {
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		ComboPooledDataSource ds = new ComboPooledDataSource();
		
		Properties connectionProps = new Properties();
		connectionProps.put("user", "postgres");
		connectionProps.put("password", "gerard");   
		ds.setProperties(connectionProps);
		ds.setJdbcUrl("jdbc:postgresql://localhost:5432/hmis");

		
		builder.bind( "java:comp/env/jdbc/hmisdb" , ds );
		try {
			builder.activate();
		} catch (Exception e) {}
		return ds;
	}
	
//	@Bean
//	 <property name="hibernate.archive.autodetection">class,hbm</property>  
//     <property name="hibernate.dialect">org.hibernate.dialect.HSQLDialect</property>  
//     <property name="hibernate.show_sql">true</property>    
//     <property name="hibernate.connection.driver_class">org.hsqldb.jdbcDriver</property>    
//     <property name="hibernate.connection.username">sa</property>    
//     <property name="hibernate.connection.password">1</property>    
//     <property name="hibernate.connection.url">jdbc:hsqldb:mem:howtodoinjava</property>    
//     <property name="hibernate.hbm2ddl.auto">create</property> 
	
	
		
	
	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.show_sql",true);
		properties.put("databasePlatform", "PostgreSQLDialectUuid");
		properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
		return properties;	
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("com.servinglynk.hmis.warehouse.model");
		sessionFactoryBean.setHibernateProperties(hibProperties());
		return sessionFactoryBean;
	}	
	
	private ObjectMapper rootMapper()
	{
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
	    mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
	    return mapper;
	}
}
