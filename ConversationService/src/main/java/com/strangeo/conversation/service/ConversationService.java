package com.strangeo.conversation.service;

import java.util.List;

import com.strangeo.conversation.entity.Conversation;

public interface ConversationService {

	public Conversation getConversation(String conversationId);

	public Conversation saveConversation(Conversation conversation);

	public List<Conversation> getConversations();

}
