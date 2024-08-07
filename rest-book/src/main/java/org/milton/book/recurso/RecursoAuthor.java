package org.milton.book.recurso;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;
import org.milton.book.servicio.ServicioAuthor;
import org.milton.book.transferible.TransferibleAuthor;
import org.milton.book.transferible.TransferibleLibro;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "author Endpoint")

public class RecursoAuthor {

    @Inject
    ServicioAuthor service;

    @Inject
    Logger LOGGER;

    //Ping
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {return "ping";}

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns all the authors from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType
            .APPLICATION_JSON, schema = @Schema(implementation = TransferibleAuthor.class, type = SchemaType
            .ARRAY)))
    @APIResponse(responseCode = "204", description = "No authors")
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetAllAuthors", description = "Counts how many times the GetAllAuthors " +
            "method has been invoked")
    @Timed(name = "timeGetAllAuthors", description = "Times how long it takes to invoke the " +
            "GetAllAuthors method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna todos los autores |----------------------
    @GET
    public Response getAllAuthors() {
        List<TransferibleAuthor> transferibleAuthorList = service.findAllAuthors();
        LOGGER.debug("Total number of books " + transferibleAuthorList.size());
        return Response.ok(transferibleAuthorList).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns an author for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType
            .APPLICATION_JSON, schema = @Schema(implementation = TransferibleAuthor.class)))
    @APIResponse(responseCode = "404", description = "The author is not found for the given identifier")
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetBook", description = "Counts how many times the GetAuthor " +
            "method has been invoked")
    @Timed(name = "timeGetBook", description = "Times how long it takes to invoke the " +
            "GetAuthor method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna el autor segun su id |----------------------
    @GET
    @Path("/{id}")
    public Response getAuthor(@Parameter(description = "Author identifier", required = true)
                            @PathParam("id")
                            Long id){
        TransferibleAuthor optionalTransferibleAuthor = service.findAuthorById(id);
        if (optionalTransferibleAuthor != null) {
            LOGGER.debug("Found author " + optionalTransferibleAuthor.getName() + " " + optionalTransferibleAuthor.getSurName());
            return Response.ok(optionalTransferibleAuthor).build();
        }else{
            LOGGER.debug("No author found with id " + id);
            return Response.status(NOT_FOUND).build();
        }
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Creates a valid author")
    @APIResponse(responseCode = "201", description = "The URI of the created author",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema
                    (implementation = URI.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countCreateAuthor", description = "Counts how many times the createAuthor " +
            "method has been invoked")
    @Timed(name = "timeCreateAuthor", description = "Times how long it takes to invoke " +
            "the createAuthor method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion POST -> Crea un autor |----------------------
    @POST
    public Response createAuthor(@RequestBody(required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TransferibleAuthor.class)))
                               @Valid TransferibleAuthor transferibleAuthor,
                               @Context UriInfo uriInfo) {
        transferibleAuthor = service.persistAuthor(transferibleAuthor);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(transferibleAuthor.getId()));
        LOGGER.debug("New author created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Updates an existing author")
    @APIResponse(responseCode = "200", description = "The updated author", content =
    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation =
            TransferibleAuthor.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countUpdateAuthor", description = "Counts how many times the updateAuthor " +
            "method has been invoked")
    @Timed(name = "timeUpdateAuthor", description = "Times how long it takes to invoke the " +
            "updateAuthor method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion PUT -> Actualiza un autor |----------------------
    @PUT
    public Response updateAuthor(@RequestBody(required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TransferibleAuthor.class)))
                               @Valid TransferibleAuthor transferibleAuthor) {

        transferibleAuthor = service.updateAuthor(transferibleAuthor);
        LOGGER.debug("Book updated with new valued " + transferibleAuthor);
        return Response.ok(transferibleAuthor).build();

    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Deletes an existing author")
    @APIResponse(responseCode = "204", description = "The author has been successfully deleted")
    //----------------------| MEtrics |----------------------
    @Counted(name = "countDeleteAuthor", description = "Counts how many times the deleteAuthor " +
            "method has been invoked")
    @Timed(name = "timeDeleteAuthor", description = "Times how long it takes to invoke the " +
            "delete method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion DELETE -> borra un autor |----------------------
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@Parameter(description = "Author identifier", required = true) @PathParam("id") Long id) {
        service.deleteAuthor(id);
        LOGGER.debug("Author deleted with " + id);
        return Response.noContent().build();
    }
}
