package com.cisco.cmad.blog.biz;

import java.util.List;

import com.cisco.cmad.blog.api.User;
import com.cisco.cmad.blog.api.UserInterface;
import com.cisco.cmad.blog.data.UserDAO;
import com.cisco.cmad.blog.data.UserJpaDao;
import com.cisco.cmad.blog.exception.BlogException;
import com.cisco.cmad.blog.exception.DuplicateInputException;
import com.cisco.cmad.blog.exception.InvalidInputException;


public class UserService implements UserInterface{

	private UserDAO dao = UserJpaDao.getInstance();

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

	@Override
	public List<User> readAllUsers() throws InvalidInputException, BlogException {
		// TODO Auto-generated method stub
		return null;
	}
}
