package com.cisco.cmad.blog.api;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class User {
	@Id
	private String userId;

	private String password;

	private String userName;

	private String emailId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date JoinDate;

	private String aboutUser;
	
	@OneToMany
	private List<Blog> blogPostList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getJoinDate() {
		return JoinDate;
	}

	public void setJoinDate(Date joinDate) {
		JoinDate = joinDate;
	}

	public String getAboutUser() {
		return aboutUser;
	}

	public void setAboutUser(String aboutUser) {
		this.aboutUser = aboutUser;
	}

	public List<Blog> getBlogPostList() {
		return blogPostList;
	}

	public void setBlogPostList(List<Blog> blogPostList) {
		this.blogPostList = blogPostList;
	}

}
