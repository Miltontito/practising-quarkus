package org.milton.book.transferible;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.milton.book.modelo.Category;

import java.util.List;

public class TransferibleCategory {

    private Long id;
    @NotNull
    private String name;
    private String parentCategory;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public @NotNull String getName() {
        return name;
    }
    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
}
