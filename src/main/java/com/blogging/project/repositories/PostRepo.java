package com.blogging.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.project.entities.Category;
import com.blogging.project.entities.Post;
import com.blogging.project.entities.User;

public interface PostRepo  extends JpaRepository <Post,Integer> {

	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List <Post> findByTitleContaining(String title);
}
