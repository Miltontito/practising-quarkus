package org.milton.book;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.milton.book.infrastructure.Database;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.milton.book.modelo.Book;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.smallrye.common.constraint.Assert.assertNotNull;
import static io.smallrye.common.constraint.Assert.assertTrue;
import static jakarta.ws.rs.core.Response.Status.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(Database.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookResourceTest {

    private static final String DEFAULT_TITLE = "Title";
    private static final String UPDATED_TITLE = "Title (updated)";
    private static final String DEFAULT_AUTHOR = "Author";
    private static final String UPDATED_AUTHOR = "Author (updated)";
    private static final Integer DEFAULT_YEAR_OF_PUBLICATION = 1111;
    private static final Integer UPDATED_YEAR_OF_PUBLICATION = 2222;
    private static final Integer DEFAULT_NB_OF_PAGES = 111;
    private static final Integer UPDATED_NB_OF_PAGES = 222;
    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;
    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(11.0);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(22.0);
    private static final URL DEFAULT_SMALL_IMAGE_URL = makeUrl("http://www.url.com");
    private static final URL UPDATED_SMALL_IMAGE_URL = makeUrl("http://www.updatedurl.com");
    private static final URL DEFAULT_MEDIUM_IMAGE_URL = makeUrl("http://www.url.com");
    private static final URL UPDATED_MEDIUM_IMAGE_URL = makeUrl("http://www.updatedurl.com");
    private static final String DEFAULT_DESCRIPTION = "Description";
    private static final String UPDATED_DESCRIPTION = "Description (updated)";

    // Mocking numbers that should be generated by the Number microservice
    public static final String MOCK_ISBN_13 = "Isbn 13";
    public static final String MOCK_ISBN_10 = "Isbn 10";

    private static URL makeUrl(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            return null;
        }
    }
    private static int nbBooks;
    private static String bookId;

    @Test
    @Order(1)
    void shouldGetInitialItems() {
        List<Book> books =
                given().
                        when()
                        .get("/api/books").
                        then()
                        .statusCode(OK.getStatusCode())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .extract().body().as(getBookTypeRef());
        nbBooks = books.size();
    }

    private TypeRef<List<Book>> getBookTypeRef() {
        return new TypeRef<>() {
        };
    }

    @Test
    @Order(2)
    void shouldAddAnItem() {
        Book book = new Book();
        book.setTitle(DEFAULT_TITLE);
        book.setIsbn13(MOCK_ISBN_13);
        book.setIsbn10(MOCK_ISBN_10);
        book.setAuthor(DEFAULT_AUTHOR);
        book.setYearOfPublication(DEFAULT_YEAR_OF_PUBLICATION);
        book.setNbOfPages(DEFAULT_NB_OF_PAGES);
        book.setRank(DEFAULT_RANK);
        book.setPrice(DEFAULT_PRICE);
        book.setSmallImageUrl(DEFAULT_SMALL_IMAGE_URL);
        book.setMediumImageUrl(DEFAULT_MEDIUM_IMAGE_URL);
        book.setDescription(DEFAULT_DESCRIPTION);

        // Persists a new book
        String location =
                given()
                        .body(book)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
                        when()
                        .post("/api/books").
                        then()
                        .statusCode(CREATED.getStatusCode())
                        .extract().header("Location");

        // Extracts the Location and stores the book id
        assertTrue(location.contains("/api/books"));
        String[] segments = location.split("/");
        bookId = segments[segments.length - 1];
        assertNotNull(bookId);

        // Checks the book has been created
        given()
                .pathParam("id", bookId).
                when()
                .get("/api/books/{id}").
                then()
                .statusCode(OK.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body("title", Is.is(DEFAULT_TITLE))
                //.body("isbn13", Is.is(MOCK_ISBN_13))
                //.body("isbn10", Is.is(MOCK_ISBN_10))
                .body("author", Is.is(DEFAULT_AUTHOR))
                .body("yearOfPublication", Is.is(DEFAULT_YEAR_OF_PUBLICATION))
                .body("nbOfPages", Is.is(DEFAULT_NB_OF_PAGES))
                .body("rank", Is.is(DEFAULT_RANK))
                .body("smallImageUrl", Is.is(DEFAULT_SMALL_IMAGE_URL.toString()))
                .body("mediumImageUrl", Is.is(DEFAULT_MEDIUM_IMAGE_URL.toString()))
                .body("description", Is.is(DEFAULT_DESCRIPTION));

        // Checks there is an extra book in the database
        List<Book> books =
                given().
                        when()
                        .get("/api/books").
                        then()
                        .statusCode(OK.getStatusCode())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .extract().body().as(getBookTypeRef());

        assertEquals(nbBooks + 1, books.size());
    }

    @Test
    @Order(3)
    void shouldUpdateAnItem() {
        Book book = new Book();
        book.setId(Long.valueOf(bookId));
        book.setTitle(UPDATED_TITLE);
        book.setIsbn13(MOCK_ISBN_13);
        book.setIsbn10(MOCK_ISBN_10);
        book.setAuthor(UPDATED_AUTHOR);
        book.setYearOfPublication(UPDATED_YEAR_OF_PUBLICATION);
        book.setNbOfPages(UPDATED_NB_OF_PAGES);
        book.setRank(UPDATED_RANK);
        book.setPrice(UPDATED_PRICE);
        book.setSmallImageUrl(UPDATED_SMALL_IMAGE_URL);
        book.setMediumImageUrl(UPDATED_MEDIUM_IMAGE_URL);
        book.setDescription(UPDATED_DESCRIPTION);

        // Updates the previously created book
        given()
                .body(book)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
                when()
                .put("/api/books").
                then()
                .statusCode(OK.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body("title", Is.is(UPDATED_TITLE))
                .body("isbn13", Is.is(MOCK_ISBN_13))
                .body("isbn10", Is.is(MOCK_ISBN_10))
                .body("author", Is.is(UPDATED_AUTHOR))
                .body("yearOfPublication", Is.is(UPDATED_YEAR_OF_PUBLICATION))
                .body("nbOfPages", Is.is(UPDATED_NB_OF_PAGES))
                .body("rank", Is.is(UPDATED_RANK))
                .body("price", Is.is(UPDATED_PRICE.intValue()))
                .body("smallImageUrl", Is.is(UPDATED_SMALL_IMAGE_URL.toString()))
                .body("mediumImageUrl", Is.is(UPDATED_MEDIUM_IMAGE_URL.toString()))
                .body("description", Is.is(UPDATED_DESCRIPTION));
    }

    @Test
    @Order(4)
    void shouldRemoveAnItem() {
        // Deletes the previously created book
        given()
                .pathParam("id", bookId).
                when()
                .delete("/api/books/{id}").
                then()
                .statusCode(NO_CONTENT.getStatusCode());

        // Checks there is less a book in the database
        List<Book> books =
                given().
                        when()
                        .get("/api/books").
                        then()
                        .statusCode(OK.getStatusCode())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .extract().body().as(getBookTypeRef());

        assertEquals(nbBooks, books.size());
    }

    @Test
    void shouldNotAddInvalidItem() {
        Book book = new Book();
        book.setTitle(null);

        given()
                .body(book)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
                when()
                .post("/api/books/").
                then()
                .statusCode(BAD_REQUEST.getStatusCode());

    }

    @Test
    void shouldPingLiveness() {
        given().
                when()
                .get("/q/health/live").
                then()
                .statusCode(OK.getStatusCode());
    }
    @Test
    void shouldPingReadiness() {
        given().
                when()
                .get("/q/health/ready").
                then()
                .statusCode(OK.getStatusCode());
    }


    @Test
    void shouldPingMetrics() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
                when()
                .get("/q/metrics/application").
                then()
                .statusCode(OK.getStatusCode());
    }

}