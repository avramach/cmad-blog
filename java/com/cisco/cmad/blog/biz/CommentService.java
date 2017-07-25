package com.cisco.cmad.blog.biz;

import com.cisco.cmad.blog.api.BlogException;

import com.cisco.cmad.blog.api.Comment;
import com.cisco.cmad.blog.api.CommentInterface;
import com.cisco.cmad.blog.api.InvalidInputException;

import com.cisco.cmad.blog.data.CommentDAO;
import com.cisco.cmad.blog.data.CommentJpaDao;

public class CommentService implements CommentInterface{

	private CommentDAO dao = new CommentJpaDao();
	
	@Override
	public Comment create(long blogId, Comment comment) throws BlogException {
        Comment newComment = null;
		if (comment == null)
            throw new BlogException();
        newComment=dao.create(blogId,comment);
	    return newComment;
	}

	@Override
	public Comment read(long id) throws InvalidInputException, BlogException {
		Comment Comment = null;
        try {
            Comment = dao.read(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
        if (Comment == null)
            throw new InvalidInputException();

        return Comment;
	}

	@Override
	public Comment update(Comment comment) throws InvalidInputException, BlogException {
		if (comment == null)
            throw new InvalidInputException();
		try {
            dao.update(comment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
        return comment;
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
