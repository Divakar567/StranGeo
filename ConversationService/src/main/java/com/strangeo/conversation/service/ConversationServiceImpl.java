package com.strangeo.conversation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.strangeo.conversation.entity.Conversation;
import com.strangeo.conversation.repo.ConversationRepository;

@Service
public class ConversationServiceImpl implements ConversationService {

	@Autowired
	private ConversationRepository conversationRepo;

	@Override
	public Conversation getConversation(String conversationId) {
		return conversationRepo.findById(conversationId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found: " + conversationId));
	}

	@Override
	public Conversation saveConversation(Conversation conversation) {
		return conversationRepo.save(conversation);
	}

	@Override
	public List<Conversation> getConversations() {
		ArrayList<Conversation> conversations = new ArrayList<>();
		conversationRepo.findAll().forEach(conversation -> conversations.add(conversation));
		return conversations;
	}

}
