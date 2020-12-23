package com.strangeo.gateway.config;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.strangeo.gateway.filter.UserAuthFilter;

@Configuration
public class GatewayRoutesConfig {

	@Autowired
	private UserAuthFilter userAuthFilter;

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("test", p -> p.path("/test/get").filters(f -> f.addRequestHeader("Hello", "World")
						.changeRequestUri(rex -> Optional.of(
								URI.create("https://httpbin.org" + rex.getRequest().getPath().subPath(2).toString()))))
						.uri("https://httpbin.org"))
				.route(p -> p.path("/api/users/**").filters(f -> f.filter(userAuthFilter)).uri("http://localhost:9090"))
				.route(p -> p.path("/api/auth/**").uri("http://localhost:9090")).build();
	}
}
