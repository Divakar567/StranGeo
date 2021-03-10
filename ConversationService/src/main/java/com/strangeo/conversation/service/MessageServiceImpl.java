package com.strangeo.conversation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.strangeo.conversation.entity.Message;
import com.strangeo.conversation.repo.MessageRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Override
	public List<Message> getMessages(String conversationId) {
		return messageRepo.findByConversationId(conversationId);
	}

	@Override
	public Message postMessage(Message message, String conversationId) {
		log.info("ConversationMessage recieved: {}", message);
		message = messageRepo.save(message);
		template.convertAndSend("/topic/conversations/" + conversationId + "/messages", message);
		return message;
	}

}
