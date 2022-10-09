package com.blogging.project.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogging.project.config.AppConstants;
import com.blogging.project.entities.Post;
import com.blogging.project.payloads.ApiResponse;
import com.blogging.project.payloads.PostDto;
import com.blogging.project.payloads.PostResponse;
import com.blogging.project.services.FileService;
import com.blogging.project.services.PostService;



@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	//Create Post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity <PostDto>createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto> (createPost,HttpStatus.CREATED);
		
		
	}
	
	//getby User
	
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		List <PostDto> post=this.postService.getPostByUser(userId);
		
		return new ResponseEntity <List<PostDto>>(post,HttpStatus.OK);
		
	}
	
	//get By Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		List <PostDto> post=this.postService.getPostByCategory(categoryId);
		
		return new ResponseEntity <List<PostDto>>(post,HttpStatus.OK);
		
	}
	
	//get all post
	
	@GetMapping("/posts")
	public ResponseEntity <PostResponse> getallPost(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.Page_Number,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.Page_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.SORT_BY, required=false) String sortBy)
	
	{
		
		PostResponse postResponse= this.postService.getAllPost(pageNumber, pageSize,sortBy);
		return new  ResponseEntity <PostResponse> (postResponse,HttpStatus.OK);
		
	}
	
	//Get Single Post
	@GetMapping("/posts/{postId}")
	public ResponseEntity <PostDto> getSinglePost(@PathVariable Integer postId){
		return ResponseEntity.ok(this.postService.getPostById(postId));
	}
	
	//delete post
		@DeleteMapping("/posts/{postId}")
		public ResponseEntity<?>  deleteCategory(@PathVariable("postId")Integer postId){
			this.postService.deletePost(postId);
			
			return new ResponseEntity(new ApiResponse("Post Deleted Succesfully",true),HttpStatus.OK);
		}
		
		//update post
		
		@PutMapping("/posts/{postId}")
		public ResponseEntity<?>  updatePost(@RequestBody PostDto postDto ,@PathVariable("postId") Integer postId) {
			PostDto updatedPost=this.postService.updatePsot(postDto, postId);
			
			return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
			
		}
		
		//search
		@GetMapping("/post/search/{keywords}")
		public ResponseEntity<List<PostDto>> searchPostByTitle(
				
				@PathVariable("keywords") String keywords
				
				){
			
			List<PostDto> posts=this.postService.getPostByKeyword(keywords);
			return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);
			
		}
		
	//post image Upload
		
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
		PostDto postDto=this.postService.getPostById(postId);
		
		String fileName= this.fileService.uploadImage(path, image);
		
		postDto.setImgName(fileName);
		PostDto updatePost=this.postService.updatePsot(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	// method to serve image files
	@GetMapping(value="post/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException{
		InputStream resource= this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
	
}
