package com.cisco.cmad.blog.api;

import java.util.ArrayList;
import java.util.List;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@XmlRootElement
@Table(name="CATEGORY_TABLE")
public class Category {

	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long categoryId;
	
	private String CategoryName;
	
	private long blogCount;
	
	@ManyToMany(mappedBy="categories")
	@XmlTransient
	private List<Blog> blogList = new ArrayList<Blog>();

	@XmlTransient
	public List<Blog> getBlogList() {
		return blogList;
	}
	
	@XmlTransient
	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public long getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(long blogCount) {
		this.blogCount = blogCount;
	}

}
