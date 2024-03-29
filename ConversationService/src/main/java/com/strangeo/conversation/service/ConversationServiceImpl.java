package com.strangeo.conversation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.strangeo.conversation.entity.Conversation;
import com.strangeo.conversation.model.ListResponse;
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
	public ListResponse<Conversation> getConversations(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Conversation> pageRecords = conversationRepo.findAll(pageable);
		
		ListResponse<Conversation> convResponse = new ListResponse<>();
		convResponse.setPage(page);
		convResponse.setSize(pageRecords.getSize());
		convResponse.setNumberOfElements(pageRecords.getNumberOfElements());
		convResponse.setTotalElements(pageRecords.getTotalElements());
		convResponse.setContent(pageRecords.getContent());
		return convResponse;
	}

}
