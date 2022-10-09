package com.blogging.project.services;

import java.util.List;

import com.blogging.project.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer id);
	void deleteUser(Integer id);
	UserDto getUserbyId(Integer id);
	List <UserDto> getallUser();
}
