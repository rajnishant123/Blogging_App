package com.blogging.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.project.entities.User;

public interface UserRepo extends JpaRepository <User,Integer>{

}
