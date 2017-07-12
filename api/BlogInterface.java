package com.cisco.cmad.blog.api;


public interface BlogInterface {
    public void create(Blog blog) throws BlogException;

    public Blog read(long id) throws InvalidInputException, BlogException;
    
    public Blog update(BlogInterface blogPost) throws InvalidInputException, BlogException;

    public void delete(long id) throws InvalidInputException, BlogException;
    
}