package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	private final CommentService commentService;

	public PostController(PostService postService,CommentService commentService) {

		this.commentService=commentService;
		this.postService = postService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	/*@PostMapping(path = "/members", consumes = "application/json", produces = "application/json")
	public void addMember(@RequestBody Member member) {
		//code
	}*/

	@PostMapping(value = "/{id}/comments", consumes = "application/json")
	public @ResponseBody ResponseEntity<String> postPostComments(@PathVariable Long id, @RequestBody NewCommentDto newCommentDto) {
		try {
			System.out.println(newCommentDto.getAuthor());
 			  commentService.addComment(newCommentDto);
			ResponseEntity response = new ResponseEntity(HttpStatus.CREATED);
			return response;
		}catch (UnsupportedOperationException exception){
			return new ResponseEntity("No such element",HttpStatus.INTERNAL_SERVER_ERROR);
		}
 	}

	@GetMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getPostComment(@PathVariable Long id) {
			return  commentService.getCommentsForPost(id);
	}

}
