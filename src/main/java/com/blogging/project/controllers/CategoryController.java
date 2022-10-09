package com.blogging.project.controllers;

import java.util.List;

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

import com.blogging.project.payloads.ApiResponse;
import com.blogging.project.payloads.CategoryDto;
import com.blogging.project.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	//Create new methods
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategoryDto =this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategoryDto,HttpStatus.CREATED);
		
	}
	//put -update
	@PutMapping("/{categoryId}")
public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer categoryid ){
		
		
		CategoryDto updatedCategory =this.categoryService.updateCategory(categoryDto,categoryid);
		return  ResponseEntity.ok(updatedCategory);
		
	}
	
	
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?>  deleteCategory(@PathVariable("categoryId")Integer categoryid){
		this.categoryService.deleteCategory(categoryid);
		
		return new ResponseEntity(new ApiResponse("Category Delted Succesfully",true),HttpStatus.OK);
	}
	
	//get -category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
	return ResponseEntity.ok(this.categoryService.getallCategory())	;
	}
	
	//get one category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
	return ResponseEntity.ok(this.categoryService.getCategorybyId(categoryId));
	}
	

}
