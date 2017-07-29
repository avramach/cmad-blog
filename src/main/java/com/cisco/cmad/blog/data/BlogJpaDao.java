package com.cisco.cmad.blog.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cisco.cmad.blog.api.Blog;
import com.cisco.cmad.blog.test.DatabaseSetup;

public class BlogJpaDao implements BlogDAO {
	private static BlogJpaDao blogJpaDao = null;
	private static EntityManagerFactory factory = null;

	public static BlogJpaDao getInstance() {
		if (blogJpaDao == null) {
			blogJpaDao = new BlogJpaDao();
			factory = Persistence.createEntityManagerFactory("blogPu");
			DatabaseSetup.addEmf(factory);
		}
		return blogJpaDao;
	}

	@Override
	public Blog create(Blog blog) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(blog);
		em.getTransaction().commit();
		em.close();
		return blog;
	}

	@Override
	public Blog read(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Blog blog = em.find(Blog.class, id);
		em.getTransaction().commit();
		em.close();
		return blog;
	}

	@Override
	public void update(Blog blog) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Blog blogUpdate = em.find(Blog.class, blog.getBlogId());
		blogUpdate.setBlogMessage(blog.getBlogMessage());
		blogUpdate.setTitle(blog.getTitle());
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Blog blog = em.find(Blog.class, id);
		em.remove(blog);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Blog readFullEntity(long id) {
		Blog blog = null;
		Blog clone = null;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		blog = em.find(Blog.class, id);
		if (blog != null) {
			clone = new Blog(blog);
		}
		em.getTransaction().commit();
		em.close();
		return clone;
	}

	@Override
	public List<Blog> readAllBlogs(int page, int size) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Blog> tquery = em.createNamedQuery(Blog.FIND_ALL, Blog.class);
		tquery.setMaxResults(size);
		tquery.setFirstResult(page * size);
		List<Blog> blogs = tquery.getResultList();
		em.getTransaction().commit();
		em.close();
		return blogs;
	}

	public List<Blog> readAllBlogsFullEntity(int page, int size) {
		List<Blog> cloneList = new ArrayList<Blog>();
		Blog clone = null;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Blog> tquery = em.createNamedQuery(Blog.FIND_ALL, Blog.class);
		tquery.setMaxResults(size);
		tquery.setFirstResult(page * size);
		List<Blog> blogs = tquery.getResultList();
		for (Blog blog : blogs) {
			clone = new Blog(blog);
			cloneList.add(clone);
		}
		em.getTransaction().commit();
		em.close();
		return blogs;
	}

	@Override
	public List<Blog> readByCategory(String category, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> readByUserId(String userId, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
