package com.university.studentservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class StudentServiceApplication {

	@Value("${address-service.baseUrl}")
	private String url;

	@Bean
	public WebClient webClient(){
		WebClient webClient= WebClient.builder()
				.baseUrl(url)
				.build();
		return webClient;
	}


	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

}
