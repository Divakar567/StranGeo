package com.strangeo.conversation.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class Entity {
	@Id
	private String id;
	@Field(name="entity_type", type = FieldType.Keyword)
	private String type;
	@Field(name="entity_source", type = FieldType.Keyword)
	private String source;
}
