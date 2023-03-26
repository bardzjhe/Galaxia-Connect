package com.g31.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.g31.demo.repository"})
public class GalaxiaConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxiaConnectApplication.class, args);
	}

}
