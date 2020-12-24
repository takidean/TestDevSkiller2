package com.devskiller.tasks.blog.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService {


	PostRepository postRepository;
 	@Autowired
	public CommentService(PostRepository postRepository){
 		this.postRepository=postRepository;
  	}
	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) throws UnsupportedOperationException{
		Optional<Post> blogPostId=postRepository.findById(postId);
		if(!blogPostId.isPresent())
			throw new UnsupportedOperationException();
		List<CommentDto> comments =blogPostId.get().getComments()
			.stream()
			.map(comment -> CommentDto.builder()
				.id(comment.getId())
				.author(comment.getAuthor())
				.comment(comment.getComment())
				.creationDate(comment.getCreationDate())
				.build())
			.collect(Collectors.toList());
		Collections.sort(comments,Comparator.comparing(CommentDto::getCreationDate).reversed());
			return comments;
 	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(NewCommentDto newCommentDto) throws UnsupportedOperationException{
		Optional<Post> blogPostId= postRepository.findById(newCommentDto.getPostId());
		System.out.println(blogPostId.isPresent());
		if(Boolean.FALSE.equals(blogPostId.isPresent())) {
			throw new UnsupportedOperationException();
		}
		Post post =blogPostId.get();
		List<Comment> comments = post.getComments();
		comments.add(Comment.builder()
			.author(newCommentDto.getAuthor())
			.comment(newCommentDto.getContent())
			.build());
		post.setComments(comments);
		postRepository.save(post);
		return post.getId();
	}
}
