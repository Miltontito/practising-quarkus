package org.milton.book.modelo;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Set;

// |Categoria| * ---> * |Categoria|

@Schema(description = "Category representation")
@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;

    @ManyToOne
    private Categoria parentCategoria;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Categoria getParentCategoria() {
        return parentCategoria;
    }
    public void setParentCategoria(Categoria parentCategoria) {
        this.parentCategoria = parentCategoria;
    }
}