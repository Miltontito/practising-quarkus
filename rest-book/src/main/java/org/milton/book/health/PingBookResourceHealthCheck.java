package org.milton.book.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.milton.book.BookResource;

@Liveness
@ApplicationScoped
public class PingBookResourceHealthCheck implements HealthCheck {

    @Inject
    BookResource bookResource;

    @Override
    public HealthCheckResponse call(){
        bookResource.ping();
        return HealthCheckResponse.named("Ping Book Rest Endpoint").up().build();
    }
}
