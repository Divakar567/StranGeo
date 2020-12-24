package com.strangeo.conversation.service;

import org.springframework.data.domain.Page;

import com.strangeo.conversation.entity.Conversation;

public interface ConversationService {

	public Conversation getConversation(String conversationId);

	public Conversation saveConversation(Conversation conversation);

	public Page<Conversation> getConversations(Integer page, Integer size);

}
