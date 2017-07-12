package com.cisco.cmad.blog.api;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tagId;
	
	private String tagName;
	
	@OneToMany
	private List<Blog> blogPostList;


	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<Blog> getBlogPostList() {
		return blogPostList;
	}

	public void setBlogPostList(List<Blog> blogPostList) {
		this.blogPostList = blogPostList;
	}

}
