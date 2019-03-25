package com.hserv.coordinatedentry.housinginventory;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.hserv.coordinatedentry.housinginventory.repository.BaseRepositoryFactoryBean;
import com.servinglynk.hmis.warehouse.client.config.SpringConfig;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(repositoryFactoryBeanClass=BaseRepositoryFactoryBean.class)
@EnableEntityLinks
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableAutoConfiguration
public class HmisApplication extends SpringBootServletInitializer {

	@Inject
    private Environment env;
	
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HmisApplication.class,SpringConfig.class);
    }
    
    @Bean
    public CommonsRequestLoggingFilter loggingFilter()
    {
    	CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
    	filter.setIncludeClientInfo(true);
    	filter.setIncludePayload(true);
    	filter.setIncludeQueryString(true);
    	filter.setMaxPayloadLength(10000);
    	filter.setAfterMessagePrefix("Request Data - [ ");
    	return filter;
    }
  	
	public static void main(String[] args) {
		SpringApplication.run(HmisApplication.class, args);
	}
}
