package com.devskiller.tasks.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class CommentDto {

	private Long id;

	private String comment;

	private String author;

	private LocalDateTime creationDate;



}
