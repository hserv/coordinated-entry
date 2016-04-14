package com.hserv.coordinatedentry.housingmatching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
/*@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.hserv.coordinatedentry.housingmatching.dao",
				"com.hserv.coordinatedentry.housingmatching.entity",
				"com.hserv.coordinatedentry.housingmatching.controller"})*/
public class Application {
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        /*System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
    }
    


}
