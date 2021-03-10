package com.strangeo.conversation.model;

import java.util.List;

import lombok.Data;

@Data
public class ListResponse<T> {
	private int page;
	private int size;
	private int numberOfElements;
	private long totalElements;
	private List<T> content;
}
