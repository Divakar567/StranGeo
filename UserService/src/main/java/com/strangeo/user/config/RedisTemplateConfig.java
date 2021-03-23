package com.strangeo.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.strangeo.user.repo.entity.UserProfile;

@Configuration
public class RedisTemplateConfig {
	
	@Bean
	public RedisTemplate<String, UserProfile> userRedisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, UserProfile> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory);
	    return template;
	}
}
