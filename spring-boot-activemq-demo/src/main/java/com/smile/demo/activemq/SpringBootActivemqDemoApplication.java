package com.smile.demo.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringBootActivemqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActivemqDemoApplication.class, args);
	}

}
