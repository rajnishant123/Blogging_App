package com.blogging.project.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.project.entities.Category;
import com.blogging.project.entities.User;
import com.blogging.project.exceptions.ResourceNotFoundException;
import com.blogging.project.payloads.CategoryDto;
import com.blogging.project.payloads.UserDto;
import com.blogging.project.repositories.CategoryRepo;
import com.blogging.project.services.CategoryService;


@Service
public class CategoryServiceImpl  implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category c= this.modelMapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(c);
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryid) {
		
		
		Category cat=this.categoryRepo.findById(categoryid).orElseThrow(() -> 
		new ResourceNotFoundException("Category","categoryid",categoryid));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDesc(categoryDto.getCategoryDesc());
		
		Category updatecat=this.categoryRepo.save(cat);
				return this.modelMapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryid) {
		Category cat=this.categoryRepo.findById(categoryid).orElseThrow(()->
		new ResourceNotFoundException("Category","categoryid",categoryid));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategorybyId(Integer categoryid) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryid).orElseThrow(()->
		new ResourceNotFoundException("Category","categoryid",categoryid));
	
		return this.modelMapper.map(cat, CategoryDto.class);

	}

	@Override
	public List<CategoryDto> getallCategory() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

}
