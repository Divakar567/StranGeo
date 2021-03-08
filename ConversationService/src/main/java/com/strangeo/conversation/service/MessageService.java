package com.strangeo.conversation.service;

import com.strangeo.conversation.entity.ConversationMessage;

public interface MessageService {

	public ConversationMessage postMessage(ConversationMessage message, String conversationId);

}
