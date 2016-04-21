package com.hserv.coordinatedentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {
	private static final Logger logger = LoggerFactory
			.getLogger(Application.class);
  public static void main(String[] args) throws Exception {
	  logger.debug("starting survey app...");
    SpringApplication.run(new Object[] { Application.class }, args);
  }
}
