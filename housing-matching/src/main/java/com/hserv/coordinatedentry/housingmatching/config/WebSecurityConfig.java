package com.hserv.coordinatedentry.housingmatching.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			    http
			      .csrf().disable()
			      .authorizeRequests()
			      .anyRequest().permitAll()
			      .and()
			      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}