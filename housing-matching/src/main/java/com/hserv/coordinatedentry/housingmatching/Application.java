package com.hserv.coordinatedentry.housingmatching;

import java.util.Calendar;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hserv.coordinatedentry.housingmatching.model.EligibilityRequirementModel;
import com.servinglynk.hmis.warehouse.client.config.SpringConfig;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableEntityLinks
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableAsync
public class Application extends SpringBootServletInitializer {
    
	

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class,SpringConfig.class);
    }
  	
	
/*    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
    }*/
    
    
    public static void main(String args[]){
    	BaseClient client = new BaseClient();
    	client.setRace(1);
    	client.setEthnicity(1);
    	client.setGender(1);
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(1986, 06, 02);

    	
    	
    	EligibilityRequirementModel model = new EligibilityRequirementModel();
    //	model.setRace("");
    }
}
