package com.cisco.cmad.blog.api;


public interface BlogInterface {
    public Blog create(Blog blog) throws BlogException,DuplicateInputException;

    public Blog read(long id) throws InvalidInputException, BlogException;
    
    public Blog update(Blog blog) throws InvalidInputException, BlogException;

    public void delete(long id) throws InvalidInputException, BlogException;
    
}