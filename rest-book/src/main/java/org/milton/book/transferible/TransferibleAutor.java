package org.milton.book.transferible;

public class TransferibleAutor {

    //---------------------| Entidad |---------------------
    private Long id;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private TransferibleLibro book;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public TransferibleLibro getBook() {
        return book;
    }
    public void setBook(TransferibleLibro book) {
        this.book = book;
    }
}
