package org.milton.quarkus.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.core.IsNot.not;

@QuarkusTest
public class NumberResourceTest {

    @Test
    void shouldGenerateBookNumber() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
                when()
                .get("/api/numbers/book").
                then()
                .statusCode(OK.getStatusCode())
                .body("$", hasKey("isbn_10"))
                .body("$", hasKey("isbn_13"))
                .body("$", hasKey("asin"))
                .body("$", hasKey("ean_8"))
                .body("$", hasKey("ean_13"))
                .body("$", not(hasKey("generationDate")));
    }

    @Test
    void shouldPingOpenApi(){
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
                when()
                .get("/q/openapi").
                then()
                .statusCode(OK.getStatusCode());
    }

    @Test
    void shouldPingSwaggerUI() {
        given().
                when()
                .get("/q/swagger-ui").
                then()
                .statusCode(OK.getStatusCode());
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
                .get("/q/metrics").
                then()
                .statusCode(OK.getStatusCode());
    }


}