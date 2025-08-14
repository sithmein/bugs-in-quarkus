package quarkus.bugs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Produces(MediaType.TEXT_PLAIN)
@Path("/routing-broken/{id2}")
public class RoutingBrokenB {
    @GET
    @Path("/some/path")
	public Response doSomething(@PathParam("id2") String id) {
	    return Response.ok(id).build();
	}
}
