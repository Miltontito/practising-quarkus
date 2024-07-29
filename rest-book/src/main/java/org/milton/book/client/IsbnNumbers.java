package org.milton.book.client;

import jakarta.json.bind.annotation.JsonbProperty;

public class IsbnNumbers {
    @JsonbProperty("isbn_10")
    private String isbn10;
    @JsonbProperty("isbn_13")
    private String isbn13;

    //getters

    public String getIsbn13(){
        return isbn13;
    }

    public String getIsbn10(){
        return isbn10;
    }

    //setters

    public IsbnNumbers setIsbn13(String isbn13){
        this.isbn13 = isbn13;
        return this;
    }

    public IsbnNumbers setIsbn10(String isbn10){
        this.isbn10 = isbn10;
        return this;
    }
}