package org.milton;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import java.time.Instant;

@Schema(description = "Several formats of book numbers")
public class BookNumbers {

    @Schema(required = true)
    @JsonbProperty("isbn_10")
    private String isbn10;

    @Schema(required = true)
    @JsonbProperty("isbn_13")
    private String isbn13;

    private String asin;

    @JsonbProperty("ean_8")
    private String ean8;

    @JsonbProperty("ean_13")
    private String ean13;

    @JsonbTransient
    private Instant generationDate;

    //Constructors
    BookNumbers(){
    }

    //Setters
    public BookNumbers setIsbn10(String isbn10){
        this.isbn10 = isbn10;
        return this;
    }
    public BookNumbers setIsbn13(String isbn13){
        this.isbn13 = isbn13;
        return this;
    }
    public BookNumbers setAsin(String asin){
        this.asin = asin;
        return this;
    }
    public BookNumbers setEan8(String ean8){
        this.ean8 = ean8;
        return this;
    }
    public BookNumbers setEan13(String ean13){
        this.ean13 = ean13;
        return this;
    }
    public BookNumbers setGenerationDate(Instant generationDate){
        this.generationDate = generationDate;
        return this;
    }

    // Getters
    public String getIsbn10() {
        return this.isbn10;
    }

    public String getIsbn13() {
        return this.isbn13;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getEan8() {
        return this.ean8;
    }

    public String getEan13() {
        return this.ean13;
    }

    public Instant getGenerationDate() {
        return this.generationDate;
    }

}