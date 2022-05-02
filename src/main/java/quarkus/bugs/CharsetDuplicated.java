package quarkus.bugs;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;


@Path("/")
public class CharsetDuplicated {
	@Provider
	@Produces(MediaType.APPLICATION_XML)
	public static class GenericXMLSerializer implements MessageBodyWriter<Object> {
	    @Override
	    public long getSize(final Object value, final Class<?> type, final Type genericType,
	        final Annotation [] annotations, final MediaType mediaType) {
	        return -1;
	    }

	    @Override
	    public boolean isWriteable(final Class<?> type, final Type genericType, final Annotation [] annotations,
	        final MediaType mediaType) {
	        return true;
	    }

	    @Override
	    public void writeTo(final Object value, final Class<?> type, final Type genericType,
	        final Annotation[] annotations, final MediaType mediaType,
	        final MultivaluedMap<String, Object> httpHeaders, final OutputStream entityStream)
	        throws IOException {
	        
    		httpHeaders.add(HttpHeaders.CONTENT_TYPE, mediaType + ";charset=ISO-8859-1");
			entityStream.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><foo>äöü</foo>"
					.getBytes(StandardCharsets.ISO_8859_1));
	    }
	}

    @POST
    @Path("charset-duplicated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response consumeJson() {
    	var map = Map.of("key", "value");
	    return Response.ok(map).build();
	}

    @POST
    @Path("charset-duplicated")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response consumeMultipart() {
    	var map = Map.of("key", "value");
	    return Response.ok(map).build();
	}
}
