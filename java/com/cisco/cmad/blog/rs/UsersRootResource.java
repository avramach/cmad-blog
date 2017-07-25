package com.cisco.cmad.blog.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.cmad.blog.api.User;
import com.cisco.cmad.blog.biz.UserService;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersRootResource {
    UserService userService = new UserService();
    
    @GET
	public Response readUsers() {
		return Response.ok().build();
	}
    @POST
	public Response create(User user) {
		return Response.ok().entity(user).build();
	}

	
	@GET
	@Path("/{userId}")
	public Response read(@PathParam("userId") String userId) {
		return Response.ok().build();
	}

	@PUT
	@Path("/{userId}")
	public Response update(User user) {
		return Response.ok().entity(user).build();
	}

	@DELETE
	@Path("/{userId}")
	public Response delete(@PathParam("userId") String userId) {
		return Response.ok().build();
	}

}
