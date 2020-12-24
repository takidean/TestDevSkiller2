package com.devskiller.tasks.blog.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue
	private Long id;

	private String comment;

	private String author;

	private LocalDateTime creationDate;



}
