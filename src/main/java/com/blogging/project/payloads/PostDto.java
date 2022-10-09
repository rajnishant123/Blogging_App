package com.blogging.project.payloads;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blogging.project.entities.Category;
import com.blogging.project.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	
	
	@NotEmpty
	@Size(min=3 ,message=" Title must Contain atleast 3 letters.")
	private String title;
	@NotEmpty
	@Size(min=3 ,message=" Content must Contain atleast 3 letters.")
	private String content;
	
	
	private String ImgName;
	
	@NotEmpty
	private UserDto user;
	@NotEmpty
	private CategoryDto category;
	@NotEmpty
	private Date addedDate;
}
