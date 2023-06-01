package com.sap.loottable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.sap.loottable.config.ItemRepository;
import com.sap.loottable.model.NewLootRequest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title = "Super Adventure Pals Loot Table API", version = "1.0", description = "Sort and display loot from Dig-led raid nights"))
public class SapLootTablesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SapLootTablesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// //dummy save an entry
		// itemRepository.save(new NewLootRequest("Yuki", "06/01/2023", "5:00PM", "stringydingy", "Abberus-Mythic", "Kazza", "penis"));

		// //fetch Loot Request
		// System.out.println("Players found with findAll():");
		// System.out.println("------------------------------");
		// for (NewLootRequest newLootRequest : itemRepository.findAll()) {
		// 	System.out.println(newLootRequest);
		// }
		// System.out.println();
	}

}
