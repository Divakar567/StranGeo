package com.strangeo.conversation.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
public class Source {
	@Field(name = "src_id", type = FieldType.Keyword)
	private String srcId;
	@Field(name = "src_type", type = FieldType.Keyword)
	private String srcType;
}
