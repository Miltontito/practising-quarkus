package org.milton.book.health;

import org.milton.book.modelo.Libro;
import org.milton.book.BookService;

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
    BookService bookService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
                .named("Book Datasource connection health check");
        try {
            List<Libro> libros = bookService.findAllBooks();
            responseBuilder.withData("Number of books in the database", libros.size()).up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }
}