package com.hserv.coordinatedentry.housingmatching;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
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
public class Application extends SpringBootServletInitializer {
    
	

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class,SpringConfig.class);
    }

    @Bean
    public SessionHelper sessionHelper() {
    	return new SessionHelper();
    }
    
    
	@Bean
	public PropertyReaderServiceImpl propertyReaderService(){
		return new PropertyReaderServiceImpl();
	}
	
	 @PostConstruct
	 public void initializeDatabasePropertySourceUsage() {
		 propertyReaderService().loadProperties("HOUSE-MATCHING-API");
	 }
    
    public static void main(String args[]){
    	
    }
}