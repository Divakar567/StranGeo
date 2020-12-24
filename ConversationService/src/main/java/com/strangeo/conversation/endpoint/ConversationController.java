package com.strangeo.conversation.endpoint;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strangeo.conversation.entity.Conversation;
import com.strangeo.conversation.service.ConversationService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/conversations")
public class ConversationController {

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private ObjectMapper objectMapper = new ObjectMapper();
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

	@PostConstruct
	public void init() {
		this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	@GetMapping("/{conversationId}")
	public ResponseEntity<Conversation> getConversation(@PathVariable(name = "conversationId") String conversationId) {
		log.info("Fetching conversation with id: {}", conversationId);
		Conversation conversation = conversationService.getConversation(conversationId);
		return new ResponseEntity<Conversation>(conversation, HttpStatus.OK);
	}

	@Validated
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Conversation> saveConversation(@RequestBody Conversation conversation) {
		conversation = conversationService.saveConversation(conversation);
		log.info("Fetching conversation with id: {}", conversation.getId());
		return new ResponseEntity<Conversation>(conversation, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Conversation>> getAllConversations(
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
		log.info("Conversation page:{}, size:{}", page, size);
		return new ResponseEntity<Page<Conversation>>(conversationService.getConversations(page, size), HttpStatus.OK);
	}

	@KafkaListener(topics = "${kafka.topic.conversations}")
	public void processConversations1(List<String> messages, Acknowledgment ack) {
		messages.forEach(message -> {
			try {
				saveConversation(objectMapper.readValue(message, Conversation.class));
			} catch (Exception e) {
				log.error("Exception while processing conversation: {}", message);
			}
			ack.acknowledge();
		});
	}

	@KafkaListener(topics = "main", groupId = "process2")
	public void processConversations2(List<String> messages, Acknowledgment ack) throws InterruptedException {
		messages.forEach(message -> log.info("Process2: {}", message));
		messages.stream().map(message -> message + ": " + System.currentTimeMillis())
				.forEach(message -> scheduler.schedule(() -> {
					try {
						kafkaTemplate.send("sg-conversations-delay-10", message);
						if (message.contains("throw")) {
							throw new RuntimeException("Ignore the message");
						}
						ack.acknowledge();
					} catch (Exception e) {
						log.info("Ignore the message");
					}
				}, 10000, TimeUnit.MILLISECONDS));
	}

	@KafkaListener(topics = "main-delay-10")
	public void processAgain(List<String> messages, Acknowledgment ack) {
		messages.forEach(message -> log.info("Process with delay: {}", message + " - " + System.currentTimeMillis()));
		ack.acknowledge();
	}
}
