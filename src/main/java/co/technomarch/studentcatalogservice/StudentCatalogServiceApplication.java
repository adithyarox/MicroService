package co.technomarch.studentcatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableMongoRepositories
public class StudentCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCatalogServiceApplication.class, args);
	}

}
