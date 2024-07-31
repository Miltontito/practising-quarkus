package org.milton.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.milton.servicio.ServicioNumero;


@Liveness
@ApplicationScoped
public class PingNumberResourceHealthCheck implements HealthCheck {

    @Inject
    ServicioNumero servicioNumero;

    @Override
    public HealthCheckResponse call(){
        servicioNumero.ping();
        return HealthCheckResponse.named("Ping Number REST Endpoint").up().build();
    }
}
