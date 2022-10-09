package com.blogging.project.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private int categoryid;
	
	@NotEmpty
	@Size(min=3 ,message=" Category name  must Contain atleast 3 letters.")
	private String categoryTitle;
	@NotEmpty
	@Size(min=4 ,message=" Category Description must not empty.")
	private String categoryDesc;
}
