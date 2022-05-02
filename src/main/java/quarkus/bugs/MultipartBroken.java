package quarkus.bugs;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;


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
