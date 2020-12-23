package com.strangeo.user.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.keys.KeyManager;
import org.springframework.security.crypto.keys.StaticKeyGeneratingKeyManager;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.UUID;

//@Configuration(proxyBeanMethods = false)
//@Import(OAuth2AuthorizationServerConfiguration.class)
@SuppressWarnings("unused")
public class AuthorizationServerConfig {

	// @formatter:off
	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("strangeo-client")
				.clientSecret("StranGeo@567")
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.redirectUri("http://localhost:3000/login/oauth2/code/strangeo")
				.redirectUri("http://localhost:3000/authorized")
				.scope(OidcScopes.OPENID)
				.scope("profile")
				.scope("email")
				.clientSettings(clientSettings -> clientSettings.requireUserConsent(true))
				.build();
		return new InMemoryRegisteredClientRepository(registeredClient);
	}
	// @formatter:on
	
	@Bean
	public KeyManager keyManager() {
		return new StaticKeyGeneratingKeyManager();
	}

//	@Bean
//	public CryptoKeySource keySource() {
//		return new StaticKeyGeneratingCryptoKeySource();
//	}

//	@Bean
//	public ProviderSettings providerSettings() {
//		return new ProviderSettings().issuer("http://auth-server:9000");
//	}
}
