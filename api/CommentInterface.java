package com.cisco.cmad.blog.api;

public interface CommentInterface {
    public void create(Comment comment) throws InvalidInputException, BlogException;

    public Comment read(long commentId) throws InvalidInputException, BlogException;

    public Comment update(Comment comment) throws InvalidInputException, BlogException;

    public void delete(long id) throws BlogException;
}
