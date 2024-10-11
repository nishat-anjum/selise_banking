package com.selise.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@SpringBootApplication(exclude = {FlywayAutoConfiguration.class})
public class SeliseBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeliseBankingApplication.class, args);
	}

}
