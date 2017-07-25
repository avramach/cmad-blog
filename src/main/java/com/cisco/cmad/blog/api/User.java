package com.cisco.cmad.blog.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@XmlRootElement
@Table(name="USER_TABLE")
public class User {
	@Id
	@Column(name="USER_ID",nullable=false,unique=true)
	private String userId;

	private String password;

	private String userName;

	private String emailId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date JoinDate;

	private String aboutUser;
	
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy="author",fetch= FetchType.LAZY)
	@XmlTransient
	private List<Blog> blogList = new ArrayList<Blog>();

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
	@XmlTransient
	public List<Blog> getBlogList() {
		return blogList;
	}
	@XmlTransient
	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

	

}
