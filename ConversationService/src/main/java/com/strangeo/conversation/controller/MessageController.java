package com.strangeo.conversation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.strangeo.conversation.entity.ConversationMessage;
import com.strangeo.conversation.service.MessageService;

@Controller
@MessageMapping("/app")
public class MessageController {
	
	@Autowired
	private MessageService messageService;

	@MessageMapping("/conversations/{conversationId}/messages")
	public void postMessage(@Payload ConversationMessage message, @DestinationVariable String conversationId) {
		messageService.postMessage(message, conversationId);
	}
}
