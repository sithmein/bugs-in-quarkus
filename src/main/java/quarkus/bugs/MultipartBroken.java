package quarkus.bugs;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class MultipartBroken {
    @POST
    @Path("multipart-broken")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJson() {
	    return Response.ok("Works").build();
	}

    @POST
    @Path("multipart-broken")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response consumeMultipart(final MultipartFormDataInput input) {
	    return Response.ok("Works").build();
	}
}
