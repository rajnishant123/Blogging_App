package com.blogging.project.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.project.entities.User;
import com.blogging.project.exceptions.ResourceNotFoundException;
import com.blogging.project.payloads.UserDto;
import com.blogging.project.repositories.UserRepo;
import com.blogging.project.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user= this.dtoToUser(userDto);
		
		User savedUser=this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",id));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setName(userDto.getName());
		User updateuser=this.userRepo.save(user);
		 UserDto userDto1=this.userToDto(updateuser);
		return null;
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",id));
		this.userRepo.delete(user);
	}

	@Override
	public UserDto getUserbyId(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getallUser() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtos; 
	}
	
	private User dtoToUser(UserDto userdto) {
		User user= this.modelMapper.map(userdto,User.class);
		/*
		 * user.setId(userdto.getId()); user.setEmail(userdto.getEmail());
		 * user.setName(userdto.getName()); user.setAbout(userdto.getAbout());
		 * user.setPassword(userdto.getPassword());
		 */
		return user;
		
	}
	public UserDto userToDto(User user) {
		
		UserDto userDto= this.modelMapper.map(user,UserDto.class);
		/*
		 * userDto.setAbout(user.getAbout()); userDto.setEmail(user.getEmail());
		 * userDto.setId(user.getId()); userDto.setName(user.getName());
		 * userDto.setPassword(user.getPassword());
		 */
		return userDto;
		
	}

}
