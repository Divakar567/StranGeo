package com.strangeo.conversation.service;

import java.util.List;

import com.strangeo.conversation.entity.Message;

public interface MessageService {

	public Message postMessage(Message message, String conversationId);

	public List<Message> getMessages(String conversationId);

}
