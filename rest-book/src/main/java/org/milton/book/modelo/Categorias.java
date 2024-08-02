/*
package org.milton.book.modelo;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(description = "Category representation")
@Entity
public class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @SequenceGenerator(sequenceName = "cat_seq",name = "category_seq")
    private Long id;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="subcategorias_id")
    private Categorias subcategoria;

    @OneToMany(mappedBy = "categorias")
    private List<Book> books;

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
    public Categorias getSubcategoria() {
        return subcategoria;
    }
    public void setSubcategoria(Categorias subcategoria) {
        this.subcategoria = subcategoria;
    }
}

 */