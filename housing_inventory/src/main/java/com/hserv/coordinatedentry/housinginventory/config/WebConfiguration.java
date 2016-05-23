package com.hserv.coordinatedentry.housinginventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
	
	@Bean 
	public WebInterceptor webInterceptorBean() {
		
		return new WebInterceptor();
	}

}
