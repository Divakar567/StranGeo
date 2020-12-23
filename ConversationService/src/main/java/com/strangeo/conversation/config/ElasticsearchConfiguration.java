package com.strangeo.conversation.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {

	@Value("${spring.elasticsearch.rest.uris}")
	private String esHosts;

	@Bean
	@Override
	public RestHighLevelClient elasticsearchClient() {
		final ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(esHosts).build();
		return RestClients.create(clientConfiguration).rest();
	}

	@Bean
	public String conversationsIndex(@Value("${elasticsearch.index.conversations}") String conversationsIndex) {
		return conversationsIndex;
	}

	@Bean
	public String messagesIndex(@Value("${elasticsearch.index.messages}") String messagesIndex) {
		return messagesIndex;
	}

}
