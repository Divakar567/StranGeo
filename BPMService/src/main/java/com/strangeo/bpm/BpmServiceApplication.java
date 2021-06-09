package com.strangeo.bpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient(autoRegister = false)
@SpringBootApplication
public class BpmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpmServiceApplication.class, args);
	}

}
