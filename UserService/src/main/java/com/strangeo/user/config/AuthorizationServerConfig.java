package com.strangeo.user.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
//import org.springframework.security.crypto.key.CryptoKeySource;
//import org.springframework.security.crypto.key.StaticKeyGeneratingCryptoKeySource;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
//
//import java.util.UUID;

//@Configuration
//@Import(OAuth2AuthorizationServerConfiguration.class)
public class AuthorizationServerConfig {
//	// @formatter:off
//	@Bean
//	public RegisteredClientRepository registeredClientRepository() {
//		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//				.clientId("messaging-client").clientSecret("secret")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//				.redirectUri("http://localhost:8080/login/oauth2/code/messaging-client-oidc")
//				.redirectUri("http://localhost:8080/authorized").scope(OidcScopes.OPENID).scope("message.read")
//				.scope("message.write").clientSettings(clientSettings -> clientSettings.requireUserConsent(true))
//				.build();
//		return new InMemoryRegisteredClientRepository(registeredClient);
//	}
//	// @formatter:on
//
//	@Bean
//	public CryptoKeySource keySource() {
//		return new StaticKeyGeneratingCryptoKeySource();
//	}
//
//	@Bean
//	public ProviderSettings providerSettings() {
//		return new ProviderSettings().issuer("http://auth-server:9000");
//	}
}
