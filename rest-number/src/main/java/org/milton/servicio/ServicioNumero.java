package org.milton.servicio;

import com.github.javafaker.Faker;
import jakarta.inject.Inject;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;
import java.time.Instant;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/numbers/book")
@Tag(name = "Number Endpoint")
public class ServicioNumero {

    //Ping
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        return "ping";
    }

    //LOGGER viejo
    //private static final Logger LOGGER = Logger.getLogger(RecursoNumero.class);
    //LOGGER nuevo
    @Inject
    Logger LOGGER;

    //Api documentation
    @Operation(summary = "Generates book numbers", description = "These books numbers have" +
            "several formats: ISBN, ASIN and EAN")
    @APIResponse(
            responseCode = "200",
            description = "",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = BookNumbers.class))
    )

    //Metrics
    @Counted(name = "countGenerateBookNumbers", description = "Counts how many times the generateBookNumbers " +
            "method has been invoked")
    @Timed(name = "timeGenerateBookNumbers", description = "Times how long it takes to invoke the " +
            "generateBookNumbers", unit = MetricUnits.MILLISECONDS)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateBookNumbers() throws InterruptedException {
        LOGGER.info("Generating book numbers");
        Faker faker = new Faker();
        BookNumbers bookNumbers = new BookNumbers();
        bookNumbers.setIsbn10(faker.code().isbn10(separator));
        bookNumbers.setIsbn13(faker.code().isbn13(separator));
        bookNumbers.setAsin(faker.code().asin());
        bookNumbers.setEan8(faker.code().ean8());
        bookNumbers.setEan13(faker.code().ean13());
        bookNumbers.setGenerationDate(Instant.now());
        return Response.ok(bookNumbers).build();
    }
}