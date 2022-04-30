package quarkus.bugs;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;

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
