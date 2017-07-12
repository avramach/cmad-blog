package com.cisco.cmad.blog.rs;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.cisco.cmad.blog.api.User;

@Path("/users")
public class UsersRootResource {

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
	public Response update(User user) {
		return Response.ok().entity(user).build();
	}

	@DELETE
	@Path("/{userId}")
	public Response delete(@PathParam("userId") String userId) {
		return Response.ok().build();
	}

}
