package org.milton.book.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email_creador;

    @Column(length = 10000)
    @Size(min = 1, max = 10000)
    private String text;
    @Min(1) @Max(10)
    private Double score;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public @NotNull String getEmail_creador() {
        return email_creador;
    }
    public void setEmail_creador(@NotNull String email_creador) {
        this.email_creador = email_creador;
    }
    public @NotNull String getText() {
        return text;
    }
    public void setText(@NotNull String text) {
        this.text = text;
    }
    public @Min(1) @Max(10) Double getScore() {
        return score;
    }
    public void setScore(@Min(1) @Max(10) Double score) {
        this.score = score;
    }
}
