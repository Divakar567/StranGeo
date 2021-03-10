package com.strangeo.conversation.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.strangeo.conversation.entity.Message;

@Repository
public interface MessageRepository extends ElasticsearchRepository<Message, String> {

	public List<Message> findByConversationId(String conversationId);
	
}
