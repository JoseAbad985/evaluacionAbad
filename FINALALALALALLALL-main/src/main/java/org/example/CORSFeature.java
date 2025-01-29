package org.example;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFeature implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        context.register(CorsFilter.class);
        return true;
    }
}