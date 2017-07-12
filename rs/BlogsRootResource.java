package com.cisco.cmad.blog.rs;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.cisco.cmad.blog.api.Blog;
import com.cisco.cmad.blog.api.Comment;


@Path("/blogs")
public class BlogsRootResource {
       
	@GET
    @Path("/{blogId}")
    public Response read(@PathParam("blogId") int blogId) {
		Blog blog =  new Blog();
		return Response.ok().entity(blog).build();
    }

	@POST
    public Response create(Blog blog) {
        return Response.ok().entity(blog).build();
    }

    @DELETE
    @Path("/{blogId}")
    public Response delete(@PathParam("blogId") long blogId) {
         return Response.ok().build();
    }
    
    @POST
    @Path("/comments")
    public Response createComment(Comment comment) {
         return Response.ok().entity(comment).build();
    }

    @GET
    @Path("/comments/{commentId}")
    public Response readComment(@PathParam("commentId") long commentId) {
         return Response.ok().build();
    }

    @DELETE
    @Path("comments/{commentId}")
    public Response deleteComment(@PathParam("commentId") long commentId) {
        return Response.ok().build();
    }

}
