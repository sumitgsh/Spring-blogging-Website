package com.example.Sampleblog.service;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Sampleblog.dto.PostDto;
import com.example.Sampleblog.model.Post;
import com.example.Sampleblog.model.User;
import com.example.Sampleblog.repository.PostRepository;

import antlr.collections.List;

@Service
public class PostService 
{

	@Autowired
	AuthService authService;
	
	@Autowired
	PostRepository postRepository;
	
	public void createPost(PostDto postDto)
	{
		
		Post post=new Post();
		
		post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		org.springframework.security.core.userdetails.User username=authService.getCurrentUser().orElseThrow(()-> new IllegalArgumentException("No user Logged In"));
		post.setUsername(username.getUsername());
		post.setCreatedOn(LocalDate.now());
		
		postRepository.save(post);	
	}
	
	public PostDto mapFromPostToDto(Post post)
	{
		PostDto postDto=new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setUserName(post.getUsername());
		return postDto;
	}
	
	
	public PostDto readSinglePost(long id)
	{
		Post post=postRepository.findById(id).orElseThrow(()->new RuntimeException("For id"+id));
		return mapFromPostToDto(post);
	}

	public java.util.List<Post> showAllPosts() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}
	
}
