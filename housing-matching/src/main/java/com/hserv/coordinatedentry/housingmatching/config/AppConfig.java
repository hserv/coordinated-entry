package com.hserv.coordinatedentry.housingmatching.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hserv.coordinatedentry.housingmatching.interceptor.AuthenticationInterceptor;
import com.hserv.coordinatedentry.housingmatching.interceptor.LoggingInterceptor;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public AuthenticationInterceptor getAuthenticationInterceptor(){
		return new AuthenticationInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingInterceptor());
		registry.addInterceptor(getAuthenticationInterceptor());
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;

	}
}
