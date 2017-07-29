package com.cisco.cmad.blog.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.cisco.cmad.blog.api.Blog;
import com.cisco.cmad.blog.api.BlogInterface.readType;
import com.cisco.cmad.blog.api.Category;
import com.cisco.cmad.blog.api.Comment;
import com.cisco.cmad.blog.api.User;
import com.cisco.cmad.blog.biz.BlogService;
import com.cisco.cmad.blog.biz.CommentService;
import com.cisco.cmad.blog.biz.UserService;

import com.cisco.cmad.blog.exception.BlogException;
import com.cisco.cmad.blog.exception.DuplicateInputException;
import com.cisco.cmad.blog.exception.InvalidInputException;

public class DatabaseSetup {

	private BlogService blogService = new BlogService();
	private UserService userService = new UserService();
	private CommentService commentService = new CommentService();
	private List<EntityManagerFactory> emfList = new ArrayList<EntityManagerFactory>();
	private static DatabaseSetup dbs =null;
	
	public static DatabaseSetup getInstance() {
		if (dbs == null) {
			dbs = new DatabaseSetup();
		}
		return dbs;
	}
	
	public static void addEmf(EntityManagerFactory fac) {
		System.out.println(" ********************* ADD----- CONNECTION**********************************");
		if(dbs==null){
			dbs = new DatabaseSetup();
		}
		dbs.emfList.add(fac);
	}
	
	public void initDatabaseSample() {

		System.out.println(" **********************DATABASE INIT START**********************************");
		User user = null;
		Category cat = null;
		Blog blog = null;
		Comment comment = null;
		List<Category> tagList = null;
		List<Blog> blogList = null;

		System.out.println("============ New User Registration==============");
		user = createUser("avi0100", "Avinash", "Udev", "udev@cisco.com");
		registerUser(user);
		user = createUser("rvem", "Rajesh", "Bangalore", "rvem@cisco.com");
		registerUser(user);
		System.out.println("============ Guest User Registration=========");
		user = createUser("guest", "GuestUser", "Anonymous", "anon@cisco.com");
		registerUser(user);

		System.out.println("============ Submit First Blog as User==============");
		user = retrieveUser("avi0100");
		printUserDetails(user);
		tagList = new ArrayList<Category>();
		cat = createCategory("Travel");
		tagList.add(cat);
		cat = createCategory("Food");
		tagList.add(cat);
		blog = createtBlog(user, "My First Blog", "Blog Message for Travel 1", tagList);
		submitBlog(blog);

		System.out.println("============= Read the Blog=========================");
		blog = retrieveBlog(1L);
		printBlogDetails(blog, readType.EAGER);

		System.out.println("=============Submit Comment======================");
		user = retrieveUser("guest");
		comment = createComment(user, "First comment");
		submitComment(blog, comment);

		System.out.println("============ Read a Blog With comment==============");
		blog = retrieveBlog(1L);
		printBlogDetails(blog, readType.EAGER);

		System.out.println("=============Submit Comment two======================");
		comment = createComment(user, "Second comment");
		submitComment(blog, comment);

		System.out.println("============ Read  Blog With two comment==============");
		blog = retrieveBlog(1L);
		printBlogDetails(blog, readType.EAGER);

		System.out.println("============ Submit Second Blog as User==============");
		user = retrieveUser("avi0100");
		printUserDetails(user);
		tagList = new ArrayList<Category>();
		cat = createCategory("Tech");
		tagList.add(cat);
		blog = createtBlog(user, "My Second Blog", "Blog Message for Technology", tagList);
		submitBlog(blog);

		System.out.println("============= Read the Blog=========================");
		blog = retrieveBlog(2L);
		printBlogDetails(blog, readType.EAGER);

		System.out.println("============ Submit First Blog as Second User==============");
		user = retrieveUser("rvem");
		printUserDetails(user);
		tagList = new ArrayList<Category>();
		cat = createCategory("Tech");
		tagList.add(cat);
		blog = createtBlog(user, "Cessna", "Blog Message on Cessna", tagList);
		submitBlog(blog);

		System.out.println("============= Read the Blog=========================");
		blog = retrieveBlog(3L);
		printBlogDetails(blog, readType.EAGER);

		user = retrieveUser("avi0100");
		printUserDetails(user);
		System.out.println("=============Submit Comment ======================");
		comment = createComment(user, "BGL18");
		submitComment(blog, comment);

		System.out.println("============= Read All Blogs=========================");

		blogList = retrieveAllBlog();
		for (Blog b : blogList) {
			printBlogDetails(b, readType.EAGER);
		}
		
		System.out.println(" **********************DATABASE INIT END**********************************");
	}

	private User createUser(String userId, String name, String about, String email) {
		User newUser = new User();
		newUser.setUserId(userId);
		newUser.setUserName(name);
		newUser.setAboutUser(about);
		newUser.setEmailId(email);
		return newUser;
	}

	private Category createCategory(String categoryName) {
		Category tag = new Category();
		tag.setCategoryName(categoryName);
		return tag;
	}

	private Comment createComment(User user, String commentString) {
		Comment newComment = new Comment();
		newComment.setCommentText(commentString);
		newComment.setAuthor(user);
		return newComment;
	}

	private Blog createtBlog(User user, String title, String content, List<Category> tags) {
		Blog newBlog = new Blog();
		newBlog.setAuthor(user);
		newBlog.setTitle(title);
		newBlog.setBlogMessage(content);
		for (Category tag : tags) {
			newBlog.getCategories().add(tag);
		}
		return newBlog;
	}

	public void submitBlog(Blog newBlog) {
		try {
			Blog blog = blogService.create(newBlog);
			System.out.println("BLOGID CREATED: " + blog.getBlogId());
		} catch (BlogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Blog retrieveBlog(long blogId) {
		Blog blog = null;
		try {
			blog = blogService.read(blogId, readType.EAGER);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BlogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blog;
	}

	private List<Blog> retrieveAllBlog() {
		List<Blog> blogList = null;
		try {
			blogList = blogService.readAllBlogs(0, 10,readType.EAGER);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BlogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blogList;
	}

	private void printBlogDetails(Blog blog, readType type) {
		System.out.println(" ********************BLOG START************************************");
		System.out.println("BlogID: " + blog.getBlogId() + " TITLE: " + blog.getTitle() + " CONTENT: " + blog.getBlogMessage());

		System.out.println(" OWNER: " + blog.getAuthor().getUserName());
		if (type == readType.EAGER) {
			for (Category tag : blog.getCategories()) {
				System.out.println(" CATEGORY: " + tag.getCategoryName());
			}
			for (Comment comment : blog.getCommentList()) {
				System.out.println(" COMMENT: " + comment.getCommentText());
			}
		}
		System.out.println(" *********************BLOG END***********************************");
	}

	private void registerUser(User user) {
		try {
			userService.create(user);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BlogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User retrieveUser(String userId) {
		User user = null;
		try {
			user = userService.read(userId);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BlogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	private void printUserDetails(User user) {
		System.out.println(" ********************************************************");
		System.out.println("UserID: " + user.getUserId() + " USERNAME: " + user.getUserName() + " EMAIL: " + user.getEmailId() + " ABOUT: "
				+ user.getAboutUser());
		System.out.println(" ********************************************************");
	}

	private void submitComment(Blog blog, Comment comment) {
		// blog.getCommentList().add(comment);
		try {
			commentService.create(blog.getBlogId(), comment);
		} catch (BlogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseSetup dbs =  DatabaseSetup.getInstance();
		dbs.initDatabaseSample();
		dbs.cleanConnections();
		System.out.println("Test JPA!");
	}

	public void cleanConnections() {
		for (EntityManagerFactory emf : emfList) {
			System.out.println(" ********************* RELEASE CONNECTION**********************************");
			emf.close();
		}
	}
}