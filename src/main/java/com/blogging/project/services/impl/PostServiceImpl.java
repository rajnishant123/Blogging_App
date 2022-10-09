package com.blogging.project.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogging.project.entities.Category;
import com.blogging.project.entities.Post;
import com.blogging.project.entities.User;
import com.blogging.project.exceptions.ResourceNotFoundException;
import com.blogging.project.payloads.CategoryDto;
import com.blogging.project.payloads.PostDto;
import com.blogging.project.payloads.PostResponse;
import com.blogging.project.repositories.CategoryRepo;
import com.blogging.project.repositories.PostRepo;
import com.blogging.project.repositories.UserRepo;
import com.blogging.project.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		
		Category category=this.catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",catId));
		Post post=this.modelMapper.map(postDto, Post.class);
		//post.setImgName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePsot(PostDto postDto, Integer postId) {
		Post p=this.postRepo.findById(postId).orElseThrow(() -> 
		new ResourceNotFoundException("Post","Post Id",postId));
		p.setTitle(postDto.getTitle());
		p.setContent(postDto.getContent());
		p.setImgName(postDto.getImgName());
		Post updatedpost=this.postRepo.save(p);
				return this.modelMapper.map(updatedpost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("Post","post Id",postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost (Integer pageNumber,Integer  pageSize,String sortBy) { 
		
		
		Pageable pages= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		
		Page <Post> pagePost=this.postRepo.findAll(pages);
		List<Post> allpost=pagePost.getContent();
		List<PostDto> allpostDtos=allpost.stream().map((p)->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		PostResponse postRes= new PostResponse();
		postRes.setContent(allpostDtos);
		postRes.setPageNumber(pagePost.getNumber());
		postRes.setPageSize(pagePost.getSize());
		postRes.setTotalrecords(pagePost.getTotalElements());
		postRes.setTotalPages(pagePost.getTotalPages());
		postRes.setIslast(pagePost.isLast());
		return postRes;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("Post","post Id",postId));
	
		return this.modelMapper.map(post, PostDto.class);


	}

	@Override
	public List <PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User","Userid",userId));
		List<Post> users=this.postRepo.findByUser(user);
		List <PostDto> postdtos=users.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List <PostDto> getPostByCategory(Integer catId) {
		// TODO Auto-generated method stub
		Category cat=this.catRepo.findById(catId).orElseThrow(()->
		new ResourceNotFoundException("Category","categoryid",catId));
		List <Post> posts=this.postRepo.findByCategory(cat);
		List <PostDto> postdtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List<PostDto> getPostByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<Post>post=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> colectedpost=post.stream().map((p)->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
		return colectedpost;
	}

}
