package quarkus.bugs;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/")
public class UnexpectedCharset {
    @POST
    @Path("unexpected-charset")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"image/png", "image/jpeg"})
	public Response consumeJson() {
    	// we look at the Accept header and then produce either PNG or JPG
    	var img = "Let's pretend this is an image".getBytes();
	    return Response.ok(img).build();
	}

    @POST
    @Path("unexpected-charset")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({"image/png", "image/jpeg"})
	public Response consumeMultipart() {
    	// we look at the Accept header and then produce either PNG or JPG    	
    	var img = "Let's pretend this is an image".getBytes();
	    return Response.ok(img).build();
	}
}
