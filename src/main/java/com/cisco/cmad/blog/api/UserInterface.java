package com.cisco.cmad.blog.api;


import java.util.List;

import com.cisco.cmad.blog.exception.BlogException;
import com.cisco.cmad.blog.exception.DuplicateInputException;
import com.cisco.cmad.blog.exception.InvalidInputException;

public interface UserInterface {
    public void create(User user) throws InvalidInputException, DuplicateInputException, BlogException;

    public User read(String userId) throws InvalidInputException, BlogException;

    public User update(User user) throws InvalidInputException, DuplicateInputException, BlogException;

    public void delete(String userId) throws InvalidInputException, BlogException;
    
    public List<User> readAllUsers() throws InvalidInputException, BlogException;
}
