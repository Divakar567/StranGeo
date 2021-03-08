package com.strangeo.conversation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.strangeo.conversation.entity.ConversationMessage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private SimpMessagingTemplate template;

	@Override
	public ConversationMessage postMessage(ConversationMessage message, String conversationId) {
		log.info("ConversationMessage recieved: {}", message);
		template.convertAndSend("/topic/conversations/" + conversationId + "/messages", message);
		return message;
	}

}
