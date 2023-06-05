package com.hobbs.tv;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info (
				title = "Hobbs Tv User-Service-app",
				description = "This app is owned by Hobbs Tv and user details is maintained in this service",
				version = "V.1.0.0",
				contact = @Contact(
						name = "Manu Shivanna",
						email = "hobbstv.info@gmail.com",
						url = "http://localhost:/api/hobbs/tv"
				)
		)
)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}


	@Bean
	public WebClient webClient()
	{
		return WebClient.builder().build();
	}

}
