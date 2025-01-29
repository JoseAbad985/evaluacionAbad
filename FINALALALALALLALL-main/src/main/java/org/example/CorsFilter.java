package org.example;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext
    ) throws IOException {
        // Add CORS headers to ALL responses
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "*"
        );
        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "true"
        );
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization, xsrf-token"
        );
        responseContext.getHeaders().add(
                "Access-Control-Expose-Headers", "xsrf-token"
        );
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD"
        );

        // Handle preflight (OPTIONS) requests explicitly
        if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
            responseContext.setStatus(Response.Status.OK.getStatusCode());
        }
    }
}