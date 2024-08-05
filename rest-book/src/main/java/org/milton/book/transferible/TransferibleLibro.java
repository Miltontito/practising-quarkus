package org.milton.book.transferible;

// Se utiliza el patron DTO

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

public class TransferibleLibro{

    //---------------------| Entidad |---------------------
    private Long id;
    @NotNull
    private String title;
    private String isbn13;
    private String isbn10;
    private Integer yearOfPublication;
    private Integer nbOfPages;
    private Integer rank;
    private BigDecimal price;
    private URL smallImageUrl;
    private URL mediumImageUrl;
    private String description;

    private List<TransferibleAutor> authors;
    private TransferibleCategoria category;
    //---------------------| Getters |---------------------

    public @NotNull String getTitle() {
        return title;
    }
    public String getIsbn13() {
        return isbn13;
    }
    public String getIsbn10() {
        return isbn10;
    }
    public Integer getYearOfPublication() {
        return yearOfPublication;
    }
    public Integer getNbOfPages() {
        return nbOfPages;
    }
    public Integer getRank() {
        return rank;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public URL getSmallImageUrl() {
        return smallImageUrl;
    }
    public URL getMediumImageUrl() {
        return mediumImageUrl;
    }
    public String getDescription() {
        return description;
    }
    public Long getId() {
        return id;
    }

    //---------------------| Setters |---------------------

    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }
    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public void setMediumImageUrl(URL mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setSmallImageUrl(URL smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }
    public void setTitle( @NotNull String title) {
        this.title = title;
    }
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<TransferibleAutor> getAuthors() {
        return authors;
    }
    public void setAuthors(List<TransferibleAutor> authors) {
        this.authors = authors;
    }
    public TransferibleCategoria getCategory() {
        return category;
    }
    public void setCategory(TransferibleCategoria category) {
        this.category = category;
    }
}
