package com.hserv.coordinatedentry.housingmatching.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hserv.coordinatedentry.housingmatching.interceptor.AuthenticationInterceptor;
import com.hserv.coordinatedentry.housingmatching.interceptor.LoggingInterceptor;

@Configuration 
public class AppConfig extends WebMvcConfigurerAdapter  { 

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingInterceptor());
	    registry.addInterceptor(new AuthenticationInterceptor());
	}    
}
