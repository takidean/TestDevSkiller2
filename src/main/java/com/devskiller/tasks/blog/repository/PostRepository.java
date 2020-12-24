package com.devskiller.tasks.blog.repository;

import com.devskiller.tasks.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devskiller.tasks.blog.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
}
