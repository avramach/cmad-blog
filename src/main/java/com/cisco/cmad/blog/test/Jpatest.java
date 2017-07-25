package com.cisco.cmad.blog.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cisco.cmad.blog.api.Blog;
import com.cisco.cmad.blog.api.Category;
import com.cisco.cmad.blog.api.Comment;
import com.cisco.cmad.blog.api.User;

public class Jpatest {

	private static void insertTest(EntityManager em) {

		System.out.println("StartBlog  Injection !");
		User newUser = new User();
		newUser.setUserId("avi0100");
		newUser.setUserName("Avinash");
		newUser.setAboutUser("Udev");
		newUser.setEmailId("udev@cisco.com");

		Category tag = new Category();
		tag.setCategoryName("Travel");
		Category tag2 = new Category();
		tag2.setCategoryName("Food");

		Category tag3 = new Category();
		tag3.setCategoryName("Science");

		Blog newBlog = new Blog();
		newBlog.setAuthor(newUser);
		newBlog.setTitle("My First Blog");
		newBlog.setBlogMessage("Blog Message for Travel 1");
		newBlog.getCategories().add(tag);
		newBlog.getCategories().add(tag2);


		System.out.println("Test Before Persist BlogID: "+newBlog.getBlogId()+" TITLE: "+newBlog.getTitle());
		em.getTransaction().begin();
		em.persist(newBlog);
		em.getTransaction().commit();
		System.out.println("Test After Persist BlogID: "+newBlog.getBlogId()+" TITLE: "+newBlog.getTitle());
		System.out.println("Done Blog Injection !");
	}


	private static void insertTestComment(EntityManager em) {

		System.out.println("Start Comment Injection !");
		User newUser2 = new User();
		newUser2.setUserId("guest");
		newUser2.setUserName("GuestUser");
		newUser2.setAboutUser("Anonymous");
		newUser2.setEmailId("anony@cisco.com");

		Comment newComment = new Comment();
		newComment.setCommentText("First comment");
		Comment newComment2 = new Comment();
		newComment2.setCommentText("Second comment");

		
		em.getTransaction().begin();
		Blog blog = em.find(Blog.class,1L);
		System.out.println("BlogID: "+blog.getBlogId()+" TITLE: "+blog.getTitle());
		blog.getCommentList().add(newComment);
		blog.getCommentList().add(newComment2);
		newComment.setAuthor(newUser2);
		//newComment.setCommentedBlog(blog);
		newComment2.setAuthor(newUser2);
		//newComment2.setCommentedBlog(blog);

		em.persist(blog);
		em.getTransaction().commit();
		System.out.println("Done Comment Injection !");
	}

	private static void readCreatedBlog(EntityManager em) {

		System.out.println("Start read Entities !");
		em.getTransaction().begin();
		Blog blog = em.find(Blog.class,1L);
		System.out.println("BlogID: "+blog.getBlogId()+" TITLE: "+blog.getTitle()+" CONTENT: "+blog.getBlogMessage()+" CATEGORY: "+blog.getCategories().get(0).getCategoryName()+" OWNER: "+blog.getAuthor().getUserName()+" COMMENT: "+blog.getCommentList().get(0).getCommentText());
		em.getTransaction().commit();
		System.out.println("Done read Entities !");
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Test JPA!");
		/*
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("blogPu");
		EntityManager em = factory.createEntityManager();
		insertTest(em);
		insertTestComment(em);
		readCreatedBlog(em);
		em.close();
		EntityManager em2 = factory.createEntityManager();
		readCreatedBlog(em2);
		em2.close();
//		factory.close();
		*/

		DatabaseFeeder df= new DatabaseFeeder();
		df.testInsertMethod();
		df.testReadMethod();
	}

}
