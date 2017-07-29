package com.cisco.cmad.blog.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import javax.persistence.Persistence;

import com.cisco.cmad.blog.api.Comment;
import com.cisco.cmad.blog.test.DatabaseSetup;


public class CommentJpaDao implements CommentDAO {
	private static EntityManagerFactory factory= null;
	  private static CommentJpaDao CommentJpaDao = null;
	    public static CommentJpaDao getInstance()
	    {
	    	if(CommentJpaDao == null){
	    		CommentJpaDao = new CommentJpaDao();
	    		factory = Persistence.createEntityManagerFactory("blogPu");	   
	    		DatabaseSetup.addEmf(factory);			
	    	}
	         return CommentJpaDao;
	    }
	@Override
	public Comment create(long blogId, Comment comment) {
		EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
        em.close();
        return comment;
	}

	@Override
	public Comment read(long commentId) {
		EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Comment comment = em.find(Comment.class, commentId);
        em.getTransaction().commit();
        em.close();
        return comment;
	}

	@Override
	public void update(Comment comment) {
		EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Comment commentUpdate = em.find(Comment.class, comment.getCommentId());
        commentUpdate.setCommentText(comment.getCommentText());
        em.getTransaction().commit();
        em.close();
	}

	@Override
	public void delete(long id) {
		EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
        em.getTransaction().commit();
        em.close();
	}
	

}
