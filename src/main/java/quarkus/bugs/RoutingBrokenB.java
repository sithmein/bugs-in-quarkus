package quarkus.bugs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Produces(MediaType.TEXT_PLAIN)
@Path("/routing-broken/{id2}")
public class RoutingBrokenB {
    @GET
    @Path("/some/path")
	public Response doSomething(@PathParam("id2") String id) {
	    return Response.ok(id).build();
	}
}
