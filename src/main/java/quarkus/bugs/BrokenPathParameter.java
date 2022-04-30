package quarkus.bugs;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class BrokenPathParameter {
    @GET
    // @Path("broken-path-parameter/{resource-id:[^a]+") // enabling this breaks the whole application
	public Response doSomething(@PathParam("resource-id") String resourceId) {
    	var map = Map.of("key", "value");
	    return Response.ok(map).build();
	}
}
