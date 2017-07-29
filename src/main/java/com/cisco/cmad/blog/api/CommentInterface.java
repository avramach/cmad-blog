package com.cisco.cmad.blog.api;

import java.util.List;

import com.cisco.cmad.blog.exception.BlogException;
import com.cisco.cmad.blog.exception.InvalidInputException;


public interface CommentInterface {
    public Comment create(long blogId,Comment comment) throws InvalidInputException, BlogException;

    public Comment read(long commentId) throws InvalidInputException, BlogException;

    public Comment update(Comment comment) throws InvalidInputException, BlogException;

    public void delete(long id) throws BlogException;
    
    public List<Comment> readAllComments(long blogId, int pageNum) throws InvalidInputException, BlogException;
}
