package com.example.Sampleblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Sampleblog.model.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

}
