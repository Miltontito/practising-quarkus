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
import org.milton.book.servicio.ServicioCategory;
import org.milton.book.transferible.TransferibleAuthor;
import org.milton.book.transferible.TransferibleCategory;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "category Endpoint")

public class RecursoCategory {

    @Inject
    ServicioCategory service;

    @Inject
    Logger LOGGER;

    //Ping
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {return "ping";}

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
        LOGGER.debug("Total number of categories " + transferibleCategoryList.size());
        return Response.ok(transferibleCategoryList).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns a category for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType
            .APPLICATION_JSON, schema = @Schema(implementation = TransferibleCategory.class)))
    @APIResponse(responseCode = "404", description = "The category is not found for the given identifier")
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetCategory", description = "Counts how many times the GetAuthor " +
            "method has been invoked")
    @Timed(name = "timeGetCategory", description = "Times how long it takes to invoke the " +
            "GetCategory method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna el autor segun su id |----------------------
    @GET
    @Path("/{id}")
    public Response getCategory(@Parameter(description = "Category identifier", required = true)
                              @PathParam("id")
                              Long id){
        TransferibleCategory optionalTransferibleCategory = service.findCategoryById(id);
        if (optionalTransferibleCategory != null) {
            LOGGER.debug("Found category " + optionalTransferibleCategory.getName());
            return Response.ok(optionalTransferibleCategory).build();
        }else{
            LOGGER.debug("No category found with id " + id);
            return Response.status(NOT_FOUND).build();
        }
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Creates a valid category")
    @APIResponse(responseCode = "201", description = "The URI of the created category",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema
                    (implementation = URI.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countCreateCategory", description = "Counts how many times the createCategory " +
            "method has been invoked")
    @Timed(name = "timeCreateCategory", description = "Times how long it takes to invoke " +
            "the createCategory method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion POST -> Crea un autor |----------------------
    @POST
    public Response createCategory(@RequestBody(required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TransferibleCategory.class)))
                                 @Valid TransferibleCategory transferibleCategory,
                                 @Context UriInfo uriInfo) {
        transferibleCategory = service.persistCategory(transferibleCategory);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(transferibleCategory.getId()));
        LOGGER.debug("New category created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Updates an existing category")
    @APIResponse(responseCode = "200", description = "The updated category", content =
    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation =
            TransferibleCategory.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countUpdateCategory", description = "Counts how many times the updateCategory " +
            "method has been invoked")
    @Timed(name = "timeUpdateCategory", description = "Times how long it takes to invoke the " +
            "updateCategory method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion PUT -> Actualiza un autor |----------------------
    @PUT
    public Response updateCategory(@RequestBody(required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TransferibleCategory.class)))
                                 @Valid TransferibleCategory transferibleCategory) {

        transferibleCategory = service.updateCategory(transferibleCategory);
        LOGGER.debug("Category updated with new valued " + transferibleCategory);
        return Response.ok(transferibleCategory).build();

    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Deletes an existing category")
    @APIResponse(responseCode = "204", description = "The category has been successfully deleted")
    //----------------------| MEtrics |----------------------
    @Counted(name = "countDeleteCategory", description = "Counts how many times the deleteCategory " +
            "method has been invoked")
    @Timed(name = "timeDeleteCategory", description = "Times how long it takes to invoke the " +
            "delete method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion DELETE -> borra un autor |----------------------
    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@Parameter(description = "Category identifier", required = true) @PathParam("id") Long id) {
        service.deleteCategory(id);
        LOGGER.debug("Category deleted with " + id);
        return Response.noContent().build();
    }
}
