package com.strangeo.conversation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.strangeo.conversation.entity.Message;
import com.strangeo.conversation.service.MessageService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/api/conversations/{conversationId}/messages")
	public @ResponseBody ResponseEntity<?> getMessages(@PathVariable String conversationId) {
		try {
			List<Message> messages = messageService.getMessages(conversationId);
			return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			log.info("Known exception while getting messages: ", e);
			throw e;
		} catch (Exception e) {
			log.info("Unknown exception while getting messages: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
		}
	}

	@MessageMapping("/conversations/{conversationId}/messages")
	public void postMessage(@Payload Message message, @DestinationVariable String conversationId) {
		try {
			messageService.postMessage(message, conversationId);
		} catch (Exception e) {
			log.info("Unknown exception while sending message: ", e);
		}
	}
}
