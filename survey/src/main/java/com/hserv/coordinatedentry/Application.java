package com.hserv.coordinatedentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAspectJAutoProxy
public class Application {
	private static final Logger logger = LoggerFactory
			.getLogger(Application.class);
  public static void main(String[] args) throws Exception {
	  logger.debug("starting survey app...");
    SpringApplication.run(new Object[] { Application.class }, args);
  }
  
  @Bean
  public Docket newsApi() {
      return new Docket(DocumentationType.SWAGGER_2)  
        .select()                                  
        .apis(RequestHandlerSelectors.basePackage("com.hserv.coordinatedentry"))              
        .paths(PathSelectors.any())                          
        .build();
  }
  
}
