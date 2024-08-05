package org.milton.book.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(description = "Comment representation")
@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email_creador;

    @Column(length = 10000)
    @Size(min = 1, max = 10000)
    private String texto;

    @Min(1) @Max(10)
    private Integer puntuacion;

    public @Size(min = 1, max = 10000) String getTexto() {
        return texto;
    }
    public void setTexto(@Size(min = 1, max = 10000) String texto) {
        this.texto = texto;
    }
    public String getEmail_creador() {
        return email_creador;
    }
    public void setEmail_creador(String email_creador) {
        this.email_creador = email_creador;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public @Min(1) @Max(10) Integer getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(@Min(1) @Max(10) Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
}