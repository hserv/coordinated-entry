package com.hserv.coordinatedentry.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.hserv.coordinatedentry.configuration.aop.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class WebConfiguration {

/*	@Bean
	ServletRegistrationBean h2ConsoleRegistration(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}*/
	
	@Bean
	LoggingAspect loggingAspect(){
		return new LoggingAspect();
	}

}
