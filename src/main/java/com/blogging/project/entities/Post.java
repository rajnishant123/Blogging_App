package com.blogging.project.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postid;
	@Column(name="post_title",nullable=false,length=100)
	private String title;
	@Column(name="post_contents",nullable=false,length=1000)
	private String content;
	
	private String imgName;
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;
	@ManyToOne
	private User user;
	
	
	
}
