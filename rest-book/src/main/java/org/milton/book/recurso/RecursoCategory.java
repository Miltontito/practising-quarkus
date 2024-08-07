package org.milton.book.recurso;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;
import org.milton.book.servicio.ServicioCategory;
import org.milton.book.transferible.TransferibleCategory;

import java.util.List;

@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "category Endpoint")

public class RecursoCategory {

    @Inject
    ServicioCategory service;

    @Inject
    Logger LOGGER;

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns all the categories from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType
            .APPLICATION_JSON, schema = @Schema(implementation = TransferibleCategory.class, type = SchemaType
            .ARRAY)))
    @APIResponse(responseCode = "204", description = "No categories")
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetAllCategories", description = "Counts how many times the GetAllCategories " +
            "method has been invoked")
    @Timed(name = "timeGetAllCategories", description = "Times how long it takes to invoke the " +
            "GetAllCategories method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna todos los autores |----------------------
    @GET
    public Response getAllCategories() {
        List<TransferibleCategory> transferibleCategoryList = service.findAllCategories();
        LOGGER.debug("Total number of books " + transferibleCategoryList.size());
        return Response.ok(transferibleCategoryList).build();
    }
}
