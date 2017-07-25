package com.cisco.cmad.blog.rs;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;






import com.cisco.cmad.blog.biz.BlogService;
import com.cisco.cmad.blog.api.Blog;
import com.cisco.cmad.blog.api.InvalidInputException;

@Path("/blogs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BlogsRootResource {
    BlogService blogService = new BlogService();       
	
	
    @GET
	@Path("/{blogId}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response readBlog(@PathParam("blogId") long id, @Context UriInfo uriInfo) {
    	System.out.println("Blog Read StartRS");//HACK
		try
		{
    	Blog blog = blogService.read(id);
		System.out.println("Blog  UserRS "+ blog.getAuthor().getUserName());//HACK
		System.out.println("Blog Read EndRS");//HACK
        return Response.ok().entity(blog).build();
		
		}
		catch(InvalidInputException i)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
    
    @GET
	public List<Blog> getBlogs(@BeanParam BlogQueryFilter filterBean) {
/*		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return blogService.getAllBlogsPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return blogService.getAllBlogsPaginated(0,10);
*/	
    return null;
	}

	@POST
	public Response addBlog(Blog blog, @Context UriInfo uriInfo) {
		
		Blog newBlog =  blogService.create(blog);
		String newId = String.valueOf(newBlog.getBlogId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newBlog)
				.build();
	}
	
	@PUT
	@Path("/{blogId}")
	public Response updateBlog(@PathParam("blogId") long id, Blog blog) {
		
		blog.setBlogId(id);
		Blog newBlog = blogService.update(blog);
		return Response.ok().entity(newBlog).build();
	}
	
	@DELETE
	@Path("/{blogId}")
	public Response deleteBlog(@PathParam("blogId") long id) {
		blogService.delete(id);
		return Response.noContent().build();
		
	}
	
	
	
	@Path("/{blogId}/comments")
	public CommentRootResource getCommentrootResource() {
		return new CommentRootResource();
	}
	

}
