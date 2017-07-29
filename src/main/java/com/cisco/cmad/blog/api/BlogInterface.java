package com.cisco.cmad.blog.api;



import java.util.List;

import com.cisco.cmad.blog.exception.BlogException;
import com.cisco.cmad.blog.exception.DuplicateInputException;
import com.cisco.cmad.blog.exception.InvalidInputException;


public interface BlogInterface {
	public enum readType {
           LAZY,EAGER
           };
    public Blog create(Blog blog) throws BlogException,DuplicateInputException;

    public Blog read(long id, readType type) throws InvalidInputException, BlogException;
    
    public Blog update(Blog blog) throws InvalidInputException, BlogException;

    public void delete(long id) throws InvalidInputException, BlogException;

    public List<Blog> readAllBlogs(int pageNum,int size,readType type) throws InvalidInputException, BlogException;

    public List<Blog> readByUserId(String userId, int pageNum) throws InvalidInputException, BlogException;
    
    public List<Blog> readByCategory(String category, int pageNum) throws InvalidInputException, BlogException;
}