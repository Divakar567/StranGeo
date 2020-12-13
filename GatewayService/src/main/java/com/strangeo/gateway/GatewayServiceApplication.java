package com.strangeo.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.strangeo.gateway.filter.UserAuthFilter;

@SpringBootApplication
public class GatewayServiceApplication {

	@Autowired
	private UserAuthFilter userAuthFilter;

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/api/users/**")
						.filters(f -> f.filter(userAuthFilter))
					.uri("http://localhost:9090"))
				.build();
	}
}
