package org.milton.book.transferible;

public class TransferibleComentario {

    //---------------------| Entidad |---------------------

    private Long id;
    private String email_creador;
    private String texto;
    private Integer puntuacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail_creador() {
        return email_creador;
    }

    public void setEmail_creador(String email_creador) {
        this.email_creador = email_creador;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
}
