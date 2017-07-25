package com.cisco.cmad.blog.biz;

import com.cisco.cmad.blog.api.BlogException;
import com.cisco.cmad.blog.api.DuplicateInputException;
import com.cisco.cmad.blog.api.InvalidInputException;
import com.cisco.cmad.blog.api.User;
import com.cisco.cmad.blog.api.UserInterface;
import com.cisco.cmad.blog.data.UserDAO;
import com.cisco.cmad.blog.data.UserJpaDao;


public class UserService implements UserInterface{

	private UserDAO dao = new UserJpaDao();

	@Override
	public void create(User user) throws InvalidInputException,
			DuplicateInputException, BlogException {
		   if (user == null)
	            throw new BlogException();
	        dao.create(user);
	}

	@Override
	public User read(String userId) throws InvalidInputException, BlogException {
		User user = null;
        try {
            user = dao.read(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
        if (user == null)
            throw new InvalidInputException();

        return user;
	}

	@Override
	public User update(User user) throws InvalidInputException,
			DuplicateInputException, BlogException {
		if (user == null)
            throw new InvalidInputException();
		try {
            dao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
        return user;

	}

	@Override
	public void delete(String userId) throws InvalidInputException,
			BlogException {
		read(userId);
		try {
            dao.delete(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
	}
}
