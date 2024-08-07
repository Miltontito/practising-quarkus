package org.milton.book.transferible;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TransferibleAuthor {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surName;
    private String nationality;


    // <--------------------------------| Getters y Setters |-------------------------------->

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getSurName() {
        return surName;
    }

    public void setSurName(@NotNull String surName) {
        this.surName = surName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
