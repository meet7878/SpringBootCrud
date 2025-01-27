package com.example.cargo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class TaskCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskCrudApplication.class, args);
	}

}
