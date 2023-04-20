package com.siddu.webapp;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		
		info=@Info(
				title = "Spring boot REST API Documentation",
				description = "Spring boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "siddu",
						email = "siddubasha255W@gmail.com",
						url = "www.youtube.com"
						),
				license = @License(
						name="siddu",
						url = ""
						
						)
				
				),
		externalDocs =@ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = ""
				
				)
		
		)

public class SpringbootRestfulWebservicesApplication {
     
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
