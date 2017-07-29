package com.cisco.cmad.blog.biz;

import java.util.List;

import com.cisco.cmad.blog.api.Blog;
import com.cisco.cmad.blog.api.BlogInterface;
import com.cisco.cmad.blog.api.User;
import com.cisco.cmad.blog.data.BlogDAO;
import com.cisco.cmad.blog.data.BlogJpaDao;
import com.cisco.cmad.blog.exception.BlogException;
import com.cisco.cmad.blog.exception.InvalidInputException;
import com.cisco.cmad.blog.test.DatabaseSetup;

public class BlogService implements BlogInterface {

	private BlogDAO dao = BlogJpaDao.getInstance();
	private static DatabaseSetup dbs = null;

	private User signOnHack() {
		User user = null;
		if (dbs == null) {
			dbs = new DatabaseSetup();
		}
		user = dbs.retrieveUser("avi0100");
		return user;
	}

	@Override
	public Blog create(Blog blog) throws BlogException {
		Blog newBlog = null;
		if (blog == null)
			throw new BlogException();
		if (blog.getAuthor() == null)
			blog.setAuthor(signOnHack());
		newBlog = dao.create(blog);
		return newBlog;
	}

	@Override
	public Blog read(long id, readType type) throws InvalidInputException, BlogException {
		Blog blog = null;
		try {
			if (type.equals(readType.LAZY)) {
				blog = dao.read(id);
			} else {
				blog = dao.readFullEntity(id);
			}

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
		try {
			dao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BlogException();
		}
	}

	@Override
	public List<Blog> readAllBlogs(int page, int size, readType type) throws InvalidInputException, BlogException {
		List<Blog> blogs = null;
		try {
			if (type == readType.LAZY) {
				blogs = dao.readAllBlogs(page, size);
			} else {
				blogs = dao.readAllBlogsFullEntity(page, size);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BlogException();
		}

		if (blogs == null || blogs.isEmpty())
			throw new InvalidInputException();
		return blogs;
	}

	@Override
	public List<Blog> readByUserId(String userId, int pageNum) throws InvalidInputException, BlogException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> readByCategory(String category, int pageNum) throws InvalidInputException, BlogException {
		// TODO Auto-generated method stub
		return null;
	}

}
