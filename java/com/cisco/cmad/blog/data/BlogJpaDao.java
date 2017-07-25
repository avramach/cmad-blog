package com.cisco.cmad.blog.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import javax.persistence.Persistence;

import com.cisco.cmad.blog.api.Blog;


public class BlogJpaDao implements BlogDAO {
	private static BlogJpaDao blogJpaDao = null;
	private static EntityManagerFactory factory= null;
	public static BlogJpaDao getInstance() {
		if (blogJpaDao == null) {
			blogJpaDao = new BlogJpaDao();
			factory = Persistence.createEntityManagerFactory("blogPu");
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
		System.out.println("Blog Cats "+ blog.getCategories().size());
		System.out.println("Blog Auth "+ blog.getAuthor());//HACK
		System.out.println("Blog Auth "+ blog.getCommentList());//HACK
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

}
