package com.cisco.cmad.blog.api;

public interface UserInterface {
    public void create(User user) throws InvalidInputException, DuplicateInputException, BlogException;

    public User read(String userId) throws InvalidInputException, BlogException;

    public User update(User user) throws InvalidInputException, DuplicateInputException, BlogException;

    public void delete(String userId) throws InvalidInputException, BlogException;
}
