
package org.milton.book.health;

import org.milton.book.acceso.AccesoLibro;
import org.milton.book.acceso.AccesoLibroInterfaz;
import org.milton.book.modelo.Book;
import org.milton.book.servicio.ServicioLibro;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {
    @Inject
    AccesoLibroInterfaz accesoLibroInterfaz;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
                .named("Book Datasource connection health check");
        try {
            List<Book> books = accesoLibroInterfaz.findAllBooks();
            responseBuilder.withData("Number of books in the database", books.size()).up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }
}