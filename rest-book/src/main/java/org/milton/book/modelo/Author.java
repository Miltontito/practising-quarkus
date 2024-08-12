package org.milton.book.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String surName;

    @Column
    private String nationality;

    @ManyToMany(mappedBy = "authors",cascade = CascadeType.ALL)
    private List<Book> books;

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
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
