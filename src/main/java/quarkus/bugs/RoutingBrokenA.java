package quarkus.bugs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Produces(MediaType.TEXT_PLAIN)
@Path("/routing-broken/{id1}")
public class RoutingBrokenA {
    @GET
    @Path("/some/other/path")
	public Response doSomething(@PathParam("id1") String id) {
	    return Response.ok(id).build();
	}
}
