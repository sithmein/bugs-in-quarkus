package quarkus.bugs;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
