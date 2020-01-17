package com.hserv.coordinatedentry.housingmatching;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hserv.coordinatedentry.housingmatching.dao.BaseRepositoryFactoryBean;
import com.hserv.coordinatedentry.housingmatching.service.impl.PropertyReaderServiceImpl;
import com.servinglynk.hmis.warehouse.client.config.SpringConfig;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(repositoryFactoryBeanClass=BaseRepositoryFactoryBean.class)
@EnableEntityLinks
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableAsync
//@Import(com.servinglynk.hmis.warehouse.client.config.SpringConfig.class)
@Import(com.servinglynk.hmis.warehouse.config.AMQConfiguration.class)
public class Application extends SpringBootServletInitializer {
    
	
	@Autowired Environment env;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class,SpringConfig.class);
    }

    @Bean
    public SessionHelper sessionHelper() {
    	return new SessionHelper();
    }
    
    public static void main(String args[]){
    	
    }
    
	@Bean
	public PropertyReaderServiceImpl propertyReader() {
		return new PropertyReaderServiceImpl();
	}

	@PostConstruct
	public void initializeDatabasePropertySourceUsage() {
		System.out.println("initializing properties");
		MutablePropertySources propertySources = ((ConfigurableEnvironment) env).getPropertySources();
		Properties properties = propertyReader().getProperties("survey-api");
		PropertiesPropertySource dbPropertySource = new PropertiesPropertySource("dbPropertySource", properties);
		propertySources.addFirst(dbPropertySource);
	}
}