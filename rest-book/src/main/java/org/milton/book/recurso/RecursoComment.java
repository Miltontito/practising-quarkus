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
import org.milton.book.servicio.ServicioComment;
import org.milton.book.transferible.TransferibleAuthor;
import org.milton.book.transferible.TransferibleComment;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "comment Endpoint")

public class RecursoComment {
    @Inject
    ServicioComment service;

    @Inject
    Logger LOGGER;

    //Ping
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {return "ping";}

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns all the comments from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType
            .APPLICATION_JSON, schema = @Schema(implementation = TransferibleComment.class, type = SchemaType
            .ARRAY)))
    @APIResponse(responseCode = "204", description = "No comments")
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetAllComments", description = "Counts how many times the GetAllComments " +
            "method has been invoked")
    @Timed(name = "timeGetAllComments", description = "Times how long it takes to invoke the " +
            "GetAllComments method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna todos los comentarios |----------------------
    @GET
    public Response getAllComments() {
        List<TransferibleComment> transferibleCommentList = service.findAllComments();
        LOGGER.debug("Total number of books " + transferibleCommentList.size());
        return Response.ok(transferibleCommentList).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns a comment for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType
            .APPLICATION_JSON, schema = @Schema(implementation = TransferibleComment.class)))
    @APIResponse(responseCode = "404", description = "The comment is not found for the given identifier")
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetComment", description = "Counts how many times the GetAComment " +
            "method has been invoked")
    @Timed(name = "timeGetComment", description = "Times how long it takes to invoke the " +
            "GetComment method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna el autor segun su id |----------------------
    @GET
    @Path("/{id}")
    public Response getComment(@Parameter(description = "Comment identifier", required = true)
                              @PathParam("id")
                              Long id){
        TransferibleComment optionalTransferibleComment = service.findCommentById(id);
        if (optionalTransferibleComment != null) {
            LOGGER.debug("Found comment " + optionalTransferibleComment);
            return Response.ok(optionalTransferibleComment).build();
        }else{
            LOGGER.debug("No comment found with id " + id);
            return Response.status(NOT_FOUND).build();
        }
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Creates a valid comment")
    @APIResponse(responseCode = "201", description = "The URI of the created comment",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema
                    (implementation = URI.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countCreateComment", description = "Counts how many times the createComment " +
            "method has been invoked")
    @Timed(name = "timeCreateComment", description = "Times how long it takes to invoke " +
            "the createComment method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion POST -> Crea un autor |----------------------
    @POST
    public Response createComment(@RequestBody(required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TransferibleComment.class)))
                                 @Valid TransferibleComment transferibleComment,
                                 @Context UriInfo uriInfo) {
        transferibleComment = service.persistComment(transferibleComment);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(transferibleComment.getId()));
        LOGGER.debug("New comment created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Updates an existing comment")
    @APIResponse(responseCode = "200", description = "The updated comment", content =
    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation =
            TransferibleComment.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countUpdateComment", description = "Counts how many times the updateComment " +
            "method has been invoked")
    @Timed(name = "timeUpdateComment", description = "Times how long it takes to invoke the " +
            "updateComment method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion PUT -> Actualiza un autor |----------------------
    @PUT
    public Response updateComment(@RequestBody(required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TransferibleComment.class)))
                                 @Valid TransferibleComment transferibleComment) {

        transferibleComment = service.updateComment(transferibleComment);
        LOGGER.debug("Book updated with new valued " + transferibleComment);
        return Response.ok(transferibleComment).build();

    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Deletes an existing comment")
    @APIResponse(responseCode = "204", description = "The comment has been successfully deleted")
    //----------------------| MEtrics |----------------------
    @Counted(name = "countDeleteAuthor", description = "Counts how many times the deleteComment " +
            "method has been invoked")
    @Timed(name = "timeDeleteComment", description = "Times how long it takes to invoke the " +
            "delete method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion DELETE -> borra un autor |----------------------
    @DELETE
    @Path("/{id}")
    public Response deleteComment(@Parameter(description = "Comment identifier", required = true) @PathParam("id") Long id) {
        service.deleteComment(id);
        LOGGER.debug("Comment deleted with " + id);
        return Response.noContent().build();
    }
}
