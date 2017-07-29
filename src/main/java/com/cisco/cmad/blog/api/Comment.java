package com.cisco.cmad.blog.api;

import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="COMMENT_TABLE")
public class Comment {
	@Id
	@Column(name="COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;
    
    private String commentText;
    
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimeStamp;

    @ManyToOne()
    @JoinColumn(name="USER_ID")
    private User author;
  
    @ManyToOne
    @JoinTable(name = "BLOG_COMMENT_TABLE")
     private Blog blogCommentOn;
    //@OneToOne
    //private Comment parentComment;

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	public Blog getBlogCommentOn() {
		return blogCommentOn;
	}

	public void setBlogCommentOn(Blog blogCommentOn) {
		this.blogCommentOn = blogCommentOn;
	}

}
