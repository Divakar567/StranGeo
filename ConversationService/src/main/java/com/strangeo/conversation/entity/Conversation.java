package com.strangeo.conversation.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "#{@conversationsIndex}")
public class Conversation {
	@Id
	private String id;
	@NotBlank
	@Field(name="subject", type = FieldType.Search_As_You_Type)
	private String subject;
	@Field(name = "description", type = FieldType.Text)
	private String description;
	@NotBlank
	@Field(name = "priority", type = FieldType.Keyword)
	private Priority priority;
	@NotBlank
	@Field(name = "status", type = FieldType.Keyword)
	private Status status;
	@Field(name = "participants", type = FieldType.Keyword)
	private Set<String> participants;
	@Field(name = "relations", type = FieldType.Nested)
	private Set<Entity> relations;
	@Field(name = "factors", type = FieldType.Nested)
	private List<Entity> factors;
	@Field(name = "source", type = FieldType.Object)
	private Source source;
	@CreatedBy
	@Field(name = "created_by", type = FieldType.Keyword)
	private String createdBy;
	@LastModifiedBy
	@Field(name = "updated_by", type = FieldType.Keyword)
	private String updatedBy;
	@CreatedDate
	@Field(name = "created_date", type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
	private Date createdDate;
	@LastModifiedDate
	@Field(name = "updated_date", type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
	private Date updatedDate;
	
	public enum Priority {
		High, Medium, Low, None
	}
	
	public enum Status {
		Open, Suspend, Closed, Terminated
	}
}
