package com.hserv.coordinatedentry.configuration;

import javax.servlet.Filter;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
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
	
/*	@Bean
	public Filter compressingFilter() {
		RestInterceptor restInterceptor = new RestInterceptor();
	    return restInterceptor;
	}*/

/*	@Bean
	public FilterRegistrationBean filterRegistrationBean () {
	    
		RestInterceptor restInterceptor = new RestInterceptor();
	    
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    
	    registrationBean.setFilter(restInterceptor);
	    registrationBean.addUrlPatterns("/**");
	    
	    return registrationBean;
	}*/
	
	@Bean 
	public WebInterceptor webInterceptorBean() {
		
		return new WebInterceptor();
	}
}
