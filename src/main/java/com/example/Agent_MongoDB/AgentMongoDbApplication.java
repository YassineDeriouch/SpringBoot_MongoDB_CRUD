package com.example.Agent_MongoDB;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgentMongoDbApplication {
	@Bean
	public ModelMapper modelMapper() { // added model mapper
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(AgentMongoDbApplication.class, args);
	}

}
