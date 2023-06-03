package com.sap.loottable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title = "Super Adventure Pals Loot Table API", version = "1.0", description = "Sort and display loot from Dig-led raid nights"))
public class SapLootTablesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapLootTablesApplication.class, args);
	}

}
