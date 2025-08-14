package quarkus.bugs;

import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class BrokenPathParameter {
    @GET
    // @Path("broken-path-parameter/{resource-id:[^a]+") // enabling this breaks the whole application
	public Response doSomething(@PathParam("resource-id") String resourceId) {
    	var map = Map.of("resource-id", resourceId);
	    return Response.ok(map).build();
	}
}
