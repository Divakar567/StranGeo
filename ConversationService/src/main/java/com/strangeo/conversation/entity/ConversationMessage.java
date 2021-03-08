package com.strangeo.conversation.entity;

import lombok.Data;

@Data
public class ConversationMessage {
	private String id;
	private Type type;
	private String payload;
	private String conversationId;
	private String sentBy;
	private String sentTo;
	private String createdTime;
	private String updatedTime;
	
	public enum Type {
		TEXT, MEDIA, EVENT
	}
}
