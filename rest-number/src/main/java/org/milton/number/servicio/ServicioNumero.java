package org.milton.number.servicio;


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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.milton.number.recurso.BookNumbersPOJO;
import org.milton.number.recurso.RecursoNumero;


@Path("/api/numbers/book")
@Tag(name = "Number Endpoint")
public class ServicioNumero {

    @Inject
    RecursoNumero recurso;

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
                    schema = @Schema(implementation = BookNumbersPOJO.class))
    )

    //Metrics
    @Counted(name = "countGenerateBookNumbers", description = "Counts how many times the generateBookNumbers " +
            "method has been invoked")
    @Timed(name = "timeGenerateBookNumbers", description = "Times how long it takes to invoke the " +
            "generateBookNumbers", unit = MetricUnits.MILLISECONDS)

    //Solicitud
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateBookNumbers() throws InterruptedException {

        //Log info
        LOGGER.info("Generating book numbers");

        //Devuelve un objeto POJO
        BookNumbersPOJO numerosGenerados = recurso.generateBookNumbers();

        //Como respuesta enviamos este objeto POJO;
        return Response.ok(numerosGenerados).build();
    }
}