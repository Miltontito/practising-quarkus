package org.milton.book.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(description = "Author representation")
@Entity
public class Autor {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Schema(required = true)
    @Column
    private String nombre;
    @NotNull
    @Column
    private String apellido;
    @Column
    private String nacionalidad;

    @ManyToOne
    private Book book;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public @NotNull String getNombre() {
        return nombre;
    }
    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }
    public @NotNull String getApellido() {
        return apellido;
    }
    public void setApellido(@NotNull String apellido) {
        this.apellido = apellido;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}