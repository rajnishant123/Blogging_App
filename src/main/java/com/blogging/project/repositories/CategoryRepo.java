package com.blogging.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.project.entities.Category;


public interface CategoryRepo extends JpaRepository <Category,Integer> {

}
