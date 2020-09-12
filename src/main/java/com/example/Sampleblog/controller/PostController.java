package com.example.Sampleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sampleblog.dto.PostDto;
import com.example.Sampleblog.model.Post;
import com.example.Sampleblog.service.PostService;

import antlr.collections.List;

@RestController

public class PostController
{
	@Autowired
	PostService postService;
	
	@PostMapping("api/posts/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto )
	{
	postService.createPost(postDto);
	
	return new ResponseEntity<>(HttpStatus.OK);
	
	}
	 		@GetMapping("api/posts/all")
	 		public ResponseEntity< java.util.List<Post>> showAllPosts() {
	        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
	 		}
	 

	    @GetMapping("api/posts/get/{id}")
	    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
	        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
	    }
	
}
