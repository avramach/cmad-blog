package com.cisco.cmad.blog.biz;

import com.cisco.cmad.blog.api.Blog;
import com.cisco.cmad.blog.api.BlogException;
import com.cisco.cmad.blog.api.BlogInterface;
import com.cisco.cmad.blog.api.InvalidInputException;
import com.cisco.cmad.blog.data.BlogDAO;
import com.cisco.cmad.blog.data.BlogJpaDao;

public class BlogService implements BlogInterface{

	private BlogDAO dao = BlogJpaDao.getInstance();
	
	@Override
	public Blog create(Blog blog) throws BlogException {
        Blog newBlog = null; 
		if (blog == null)
            throw new BlogException();
        newBlog=dao.create(blog);
        return newBlog;
	}

	@Override
	public Blog read(long id) throws InvalidInputException, BlogException {
		Blog blog = null;
        try {
            blog = dao.read(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
        if (blog == null)
            throw new InvalidInputException();

        return blog;
	}

	@Override
	public Blog update(Blog blog) throws InvalidInputException, BlogException {
		if (blog == null)
            throw new InvalidInputException();
		try {
            dao.update(blog);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
        return blog;
	}

	@Override
	public void delete(long id) throws InvalidInputException, BlogException {
		read(id);
		try {
            dao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
	}

}
