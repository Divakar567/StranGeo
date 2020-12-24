package com.strangeo.conversation.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.strangeo.conversation.entity.Conversation;

@Repository
public interface ConversationRepository extends ElasticsearchRepository<Conversation, String> {

	public Page<Conversation> findAll(Pageable pageable);
	
}
