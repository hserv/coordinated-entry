package com.hserv.coordinatedentry.housinginventory.config;

import org.springframework.context.annotation.*;

import com.hserv.coordinatedentry.housinginventory.aop.logging.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

   @Bean
   
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
