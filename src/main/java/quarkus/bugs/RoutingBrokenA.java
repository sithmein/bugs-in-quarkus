package quarkus.bugs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Produces(MediaType.TEXT_PLAIN)
@Path("/routing-broken/{id1}")
public class RoutingBrokenA {
    @GET
    @Path("/some/other/path")
	public Response doSomething(@PathParam("id1") String id) {
	    return Response.ok(id).build();
	}
}
