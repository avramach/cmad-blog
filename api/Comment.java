package com.cisco.cmad.blog.api;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;
    
    private String commentText;
    
	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    @ManyToOne
    private User author;

    @ManyToOne
    private Blog commentedBlogPost;
    
    @OneToOne
    private Comment parentComment;

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

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public Blog getCommentedBlogPost() {
		return commentedBlogPost;
	}

	public void setCommentedBlogPost(Blog commentedBlogPost) {
		this.commentedBlogPost = commentedBlogPost;
	}

}
