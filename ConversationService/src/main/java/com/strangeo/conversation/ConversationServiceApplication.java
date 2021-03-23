package com.strangeo.conversation;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.context.SecurityContextHolder;

@EnableElasticsearchAuditing(auditorAwareRef = "auditorAware")
@EnableScheduling
@EnableElasticsearchRepositories
@EnableDiscoveryClient(autoRegister = false)
@SpringBootApplication
public class ConversationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversationServiceApplication.class, args);
	}
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAware<String>() {
			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
			}
		};
	}

}
