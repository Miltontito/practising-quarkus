package org.milton.book.transferible;

import jakarta.persistence.ManyToOne;

import java.util.Set;

public class TransferibleCategoria {
    //---------------------| Entidad |---------------------
    private Long id;
    private String name;
    private TransferibleCategoria parentCategoria;
    private Set<TransferibleComentario> subCategoria;


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

    public TransferibleCategoria getParentCategoria() {
        return parentCategoria;
    }

    public void setParentCategoria(TransferibleCategoria parentCategoria) {
        this.parentCategoria = parentCategoria;
    }

    public Set<TransferibleComentario> getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(Set<TransferibleComentario> subCategoria) {
        this.subCategoria = subCategoria;
    }
}
