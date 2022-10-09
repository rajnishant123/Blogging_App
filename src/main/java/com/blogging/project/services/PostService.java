package com.blogging.project.services;

import java.util.List;

import com.blogging.project.entities.Category;
import com.blogging.project.entities.Post;
import com.blogging.project.entities.User;
import com.blogging.project.payloads.PostDto;
import com.blogging.project.payloads.PostResponse;

public interface PostService {
	
	
	
	//Create
	PostDto createPost(PostDto postDto, Integer userId,Integer catId);
	
	
	//Update
	PostDto updatePsot(PostDto postDto,Integer postId);
	
	
	//Delete
	void deletePost(Integer postId);
	
	//Get All Post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	
	//Get Single Post
	PostDto getPostById(Integer postId);
	
	
	//Get All Posts by User
	
	List <PostDto> getPostByUser(Integer userId);
	
	//Get All Post By Category
	List <PostDto> getPostByCategory(Integer catId);
	
	
	//SearchPost
	
	List<PostDto> getPostByKeyword(String keyword);
	
}
