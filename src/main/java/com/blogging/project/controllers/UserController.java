package com.blogging.project.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.project.entities.User;
import com.blogging.project.payloads.ApiResponse;
import com.blogging.project.payloads.UserDto;
import com.blogging.project.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	//Create new methods
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createUserDto =this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
	//put -update
	@PutMapping("/{userId}")
public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid ){
		
		
		UserDto updatedUser =this.userService.updateUser(userDto,uid);
		return  ResponseEntity.ok(updatedUser);
		
	}
	
	
	
	//delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<?>  deleteUser(@PathVariable("userId")Integer uid){
		this.userService.deleteUser(uid);
		
		return new ResponseEntity(new ApiResponse("User Delted Succesfully",true),HttpStatus.OK);
	}
	
	//get -user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
	return ResponseEntity.ok(this.userService.getallUser())	;
	}
	
	//get one User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId){
	return ResponseEntity.ok(this.userService.getUserbyId(userId));
	}
	
	
}



