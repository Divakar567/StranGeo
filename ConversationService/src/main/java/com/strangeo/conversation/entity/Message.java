package com.strangeo.conversation.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "#{@messagesIndex}")
public class Message {
	@Id
	private String id;
	@NotBlank
	@Field(name = "type", type = FieldType.Keyword)
	private Type type;
	@NotBlank
	@Field(name="payload", type = FieldType.Search_As_You_Type)
	private String payload;
	@NotBlank
	@Field(name = "conversation_id", type = FieldType.Keyword)
	private String conversationId;
	@NotBlank
	@Field(name = "sent_by", type = FieldType.Keyword)
	private String sentBy;
	@CreatedDate
	@Field(name = "created_date", type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
	private Date createdDate = new Date();
	@LastModifiedDate
	@Field(name = "updated_date", type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
	private Date updatedDate = new Date();
	
	public enum Type {
		TEXT, MEDIA, EVENT
	}
}
