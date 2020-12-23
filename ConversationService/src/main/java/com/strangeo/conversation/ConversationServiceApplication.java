package com.strangeo.conversation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@EnableDiscoveryClient(autoRegister = false)
@SpringBootApplication
public class ConversationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversationServiceApplication.class, args);
	}

}
