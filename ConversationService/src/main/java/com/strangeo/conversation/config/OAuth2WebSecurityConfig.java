package com.strangeo.conversation.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static KeycloakGrantedAuthoritiesConverter grantedAuthoritiesConverter = new KeycloakGrantedAuthoritiesConverter();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
		bearerTokenResolver.setAllowUriQueryParameter(true);

		http.cors().and()
				.authorizeRequests(
						auth -> auth.antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(config -> config.jwtAuthenticationConverter(converter).and()
						.bearerTokenResolver(bearerTokenResolver)));
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.applyPermitDefaultValues();
				config.setAllowedOrigins(Collections.unmodifiableList(
						Collections.singletonList("http://localhost:3000")));
				config.setAllowCredentials(true);
				return config;
			}
		};
    }

	public static KeycloakGrantedAuthoritiesConverter getGrantedAuthoritiesConverter() {
		return grantedAuthoritiesConverter;
	}

	public static class KeycloakGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

		private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

		@Override
		public Collection<GrantedAuthority> convert(Jwt source) {
			Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(source);
			authorities.addAll(extractRoles(source));
			return authorities;
		}

		public Collection<? extends GrantedAuthority> extractRoles(Jwt source) {
			Collection<GrantedAuthority> roleAuthorities = new HashSet<>();
			Map<String, List<String>> realm_access = source.getClaim("realm_access");
			for (String role : realm_access.get("roles")) {
				roleAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			}
			return roleAuthorities;
		}
	}
}
