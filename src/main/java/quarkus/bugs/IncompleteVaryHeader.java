package quarkus.bugs;

import java.io.IOException;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Produces(MediaType.TEXT_PLAIN)
@Path("/")
public class IncompleteVaryHeader {
    public static class VaryOriginFilter {
        @RouteFilter
        void checkForCSRF(final RoutingContext rc) {
            rc.response().headers().add("Vary", "Origin");
            rc.next();
        }
    }
    
    @Provider
    public static class NoCacheControlFilter implements ContainerResponseFilter {
        @Override
        public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
            throws IOException {
            responseContext.getHeaders().add(HttpHeaders.VARY, "Prefer");
        }
    }

    @GET
    @Path("incomplete-vary-header")
    public Response doSometing() {
        return Response.ok("Test").type(MediaType.TEXT_PLAIN).build();
    }
}
