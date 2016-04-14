package com.hserv.coordinatedentry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(new Object[] { Application.class }, args);
  }
}
