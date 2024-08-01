package org.milton.book.recurso;

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

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.milton.book.modelo.Book;
import org.milton.book.servicio.ServicioLibro;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Book Endpoint")

public class RecursoLibro {

    //Ping
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "ping";
    }

    //Injeccion del BookService como service.
    @Inject
    ServicioLibro service;

    @Inject
    Logger LOGGER;

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns a random book")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetRandomBook", description = "Counts how many times the GetRandomBook " +
            "method has been invoked")
    @Timed(name = "timeGetRandomBook", description = "Times how long it takes to invoke the " +
            "GetRandomBook method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna un libro aleatorio |----------------------
    @GET
    @Path("/random")
    public Response getRandomBook() {

        //Retorna un objeto Libro random.
        Book book = service.findRandomBook();

        //Log info
        LOGGER.debug("Found random book " + book);

        //Responde con el libro.
        return Response.ok(book).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns all the books from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType
            .APPLICATION_JSON, schema = @Schema(implementation = Book.class, type = SchemaType
            .ARRAY)))
    @APIResponse(responseCode = "204", description = "No books")
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetAllBooks", description = "Counts how many times the GetAllBooks " +
            "method has been invoked")
    @Timed(name = "timeGetAllBooks", description = "Times how long it takes to invoke the " +
            "GetAllBooks method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna todos los libros |----------------------
    @GET
    public Response getAllBooks() {
        List<Book> books = service.findAllBooks();
        LOGGER.debug("Total number of books " + books);
        return Response.ok(books).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Returns a book for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType
            .APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
    @APIResponse(responseCode = "404", description = "The book is not found for the given identifier")
    //----------------------| Metrics |----------------------
    @Counted(name = "countGetBook", description = "Counts how many times the GetBook " +
            "method has been invoked")
    @Timed(name = "timeGetBook", description = "Times how long it takes to invoke the " +
            "GetBook method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion GET -> retorna el libro segun su id |----------------------
    @GET
    @Path("/{id}")
    public Response getBook(@Parameter(description = "Book identifier", required = true)
                            @PathParam("id")
                            Long id){
        Optional<Book> book = service.findBookById(id);
        if (book.isPresent()) {
            LOGGER.debug("Found book " + book);
            return Response.ok(book).build();
        }else{
            LOGGER.debug("No book found with id " + id);
            return Response.status(NOT_FOUND).build();
        }
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Creates a valid book")
    @APIResponse(responseCode = "201", description = "The URI of the created book",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema
                    (implementation = URI.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countCreateBook", description = "Counts how many times the createBook " +
            "method has been invoked")
    @Timed(name = "timeCreateBook", description = "Times how long it takes to invoke " +
            "the createBook method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion POST -> Crea un libro |----------------------
    @POST
    public Response createBook(@RequestBody(required = true,
                                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = Book.class)))
                                @Valid Book book,
                                @Context UriInfo uriInfo) {
        book = service.persistBook(book);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id));
        LOGGER.debug("New book created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Updates an existing book")
    @APIResponse(responseCode = "200", description = "The updated book", content =
    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation =
            Book.class)))
    //----------------------| Metrics |----------------------
    @Counted(name = "countUpdateBook", description = "Counts how many times the updateBook " +
            "method has been invoked")
    @Timed(name = "timeUpdateBook", description = "Times how long it takes to invoke the " +
            "updateBook method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion PUT -> Actualiza un libro |----------------------
    @PUT
    public Response updateBook(@RequestBody(required = true, content = @Content(mediaType
            = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Book.class))) @Valid
                               Book book) {
        book = service.updateBook(book);
        LOGGER.debug("Book updated with new valued " + book);
        return Response.ok(book).build();
    }

    //----------------------| Documentación API |----------------------
    @Operation(summary = "Deletes an existing book")
    @APIResponse(responseCode = "204", description = "The book has been successfully deleted")
    //----------------------| MEtrics |----------------------
    @Counted(name = "countDeleteBook", description = "Counts how many times the deleteBook " +
            "method has been invoked")
    @Timed(name = "timeDeleteBook", description = "Times how long it takes to invoke the " +
            "delete method", unit = MetricUnits.MILLISECONDS)
    //----------------------| Peticion DELETE -> borra un libro |----------------------
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
        service.deleteBook(id);
        LOGGER.debug("Book deleted with " + id);
        return Response.noContent().build();
    }
}
