package com.blogging.project.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	
	@NotEmpty
	@Size(min=3 ,message=" Name must Contain 3 letters")
	private String name;
	@Email(message=" Please enter a valid email address")
	private String email;
	@NotEmpty
	@Size(min=3 , max=10, message=" Password must have minimum 3 characters and maximum 10 characters")
	private String password;
	@NotEmpty
	private String about;

}
