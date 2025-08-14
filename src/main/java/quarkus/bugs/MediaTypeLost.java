package quarkus.bugs;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;


@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class MediaTypeLost {
	@Provider
	@Produces(MediaType.APPLICATION_JSON)
	public static class GenericJSONSerializer implements MessageBodyWriter<Object> {
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
	        
	    	httpHeaders.add("Copy-Of-Content-Type", mediaType.toString());
	    	new ObjectMapper().writer().writeValue(entityStream, value);
	    }
	}

    @GET
    @Path("media-type-lost")
	public Response doSomething() {
    	var map = Map.of("key", "value");
	    return Response.ok(map).build();
	}
}
