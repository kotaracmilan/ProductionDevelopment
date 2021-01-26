package com.kota.pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class ProductionDevelopmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductionDevelopmentApplication.class, args);
	}

}
