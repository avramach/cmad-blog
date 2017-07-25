package com.cisco.cmad.blog.rs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cisco.cmad.blog.api.Comment;
import com.cisco.cmad.blog.biz.CommentService;


public class CommentRootResource {

	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public class CommentResource {

		private CommentService commentService = new CommentService();
		
		@GET
		public List<Comment> getComments(@PathParam("blogId") long blogId) {
			//return commentService.getAllComments(blogId);
			return null;
		}
		
		@POST
		public Comment addComment(@PathParam("blogId") long blogId, Comment comment) {
			return commentService.create(blogId, comment);
		}
		
		@PUT
		@Path("/{commentId}")
		public Comment updateComment(@PathParam("blogId") long blogId, @PathParam("commentId") long id, Comment comment) {
			comment.setCommentId(id);
			return commentService.update(comment);
		}
		
		@DELETE
		@Path("/{commentId}")
		public void deleteComment(@PathParam("blogId") long blogId, @PathParam("commentId") long commentId) {
			commentService.delete(commentId);
		}
		
		
		@GET
		@Path("/{commentId}")
		public Comment getMessage(@PathParam("blogID") long blogId, @PathParam("commentId") long commentId) {
			return commentService.read(commentId);
		}
		
	}

}
