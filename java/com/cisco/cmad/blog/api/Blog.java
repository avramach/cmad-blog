package com.cisco.cmad.blog.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="BLOG_TABLE")
public class Blog {
	
	@Id
	@Column(name="BLOG_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long blogId;

	private String title;

	private String blogMessage;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	private long viewCount;
	
	private long likes;

	@JoinColumn(name="USER_ID")
	@ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	private User author;

	
	@ManyToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST},fetch= FetchType.LAZY)
	@JoinTable(name="BLOG_CATEGORY_TABLE")
	@XmlTransient
	private List<Category> categories = new ArrayList<Category>();

	@OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST},mappedBy="commentedBlog",fetch= FetchType.LAZY)
	@XmlTransient
	private List<Comment> commentList = new ArrayList<Comment>();

	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getBlogMessage() {
		return blogMessage;
	}

	public void setBlogMessage(String blogMessage) {
		this.blogMessage = blogMessage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
