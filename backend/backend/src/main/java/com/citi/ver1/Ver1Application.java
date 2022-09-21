package com.citi.ver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.citi.ver1.BL.*;
@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableAsync
@EntityScan(basePackages= {"com.citi.ver1.dto"})

public class Ver1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Ver1Application.class, args);

		
		
	}

}
