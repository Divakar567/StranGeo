package com.strangeo.conversation.service;

import com.strangeo.conversation.entity.Conversation;
import com.strangeo.conversation.model.ListResponse;

public interface ConversationService {

	public Conversation getConversation(String conversationId);

	public Conversation saveConversation(Conversation conversation);

	public ListResponse<Conversation> getConversations(Integer page, Integer size);

}
