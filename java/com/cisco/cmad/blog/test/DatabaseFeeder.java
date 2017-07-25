package com.cisco.cmad.blog.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.cisco.cmad.blog.api.Blog;
import com.cisco.cmad.blog.api.Category;
import com.cisco.cmad.blog.api.Comment;
import com.cisco.cmad.blog.api.User;
import com.cisco.cmad.blog.biz.BlogService;


@WebListener
public class DatabaseFeeder implements ServletContextListener {

	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		testInsertMethod();
		testReadMethod();
	}// end contextInitialized method

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	
	}// end constextDestroyed method

	
	public void testInsertMethod() {
		System.out.println("StartBlog  Injection !");
		User newUser = new User();
		newUser.setUserId("avi0100");
		newUser.setUserName("Avinash");
		newUser.setAboutUser("Udev");
		newUser.setEmailId("udev@cisco.com");

		User newUser2 = new User();
		newUser2.setUserId("guest");
		newUser2.setUserName("GuestUser");
		newUser2.setAboutUser("Anonymous");
		newUser2.setEmailId("anony@cisco.com");

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

		Comment newComment = new Comment();
		newComment.setCommentText("First comment");
		Comment newComment2 = new Comment();
		newComment2.setCommentText("Second comment");

		newComment.setAuthor(newUser2);
		newComment.setCommentedBlog(newBlog);
		newComment2.setAuthor(newUser2);
		newComment2.setCommentedBlog(newBlog);
		newBlog.getCommentList().add(newComment);
		newBlog.getCommentList().add(newComment2);

		BlogService blogService = new BlogService();
		blogService.create(newBlog);

		System.out.println("PersistInit BlogID: " + newBlog.getBlogId()
				+ " TITLE: " + newBlog.getTitle());

	}

	public void testReadMethod() {

		System.out.println("Start read Entities !");
		BlogService blogService = new BlogService();
		Blog blog = blogService.read(1L);
		System.out.println(" BlogID: " + blog.getBlogId()
				+ " TITLE: " + blog.getTitle());

		System.out.println("BlogID: " + blog.getBlogId() + " TITLE: "
				+ blog.getTitle() + " CONTENT: " + blog.getBlogMessage()
				+ " CATEGORY: " + blog.getCategories().get(0).getCategoryName()
				+ " OWNER: " + blog.getAuthor().getUserName() + " COMMENT: "
				+ blog.getCommentList().get(0).getCommentText());
		System.out.println("Done read Entities !");

	}

	}