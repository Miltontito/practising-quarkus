
package org.milton.book.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.milton.book.recurso.RecursoLibro;

@Liveness
@ApplicationScoped
public class PingBookResourceHealthCheck implements HealthCheck {

    @Inject
    RecursoLibro recursoLibro;

    @Override
    public HealthCheckResponse call(){
        recursoLibro.ping();
        return HealthCheckResponse.named("Ping Book Rest Endpoint").up().build();
    }
}
