package com.tindev.tindevapi;

import com.tindev.tindevapi.repository.mongodb.UserRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TindevApiApplication {

	@Autowired
	private UserRepositoryMongo userRepositoryMongo;

	public static void main(String[] args) {
		SpringApplication.run(TindevApiApplication.class, args);
	}


}
