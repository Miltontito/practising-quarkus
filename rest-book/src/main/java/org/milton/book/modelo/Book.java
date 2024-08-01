package org.milton.book.modelo;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.net.URL;


@Schema(description = "Book representation")
@Entity
public class Book{

    //---------------------| Entidad |---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_sequence")
    private Long id;
    @NotNull
    @Schema(required = true)
    private String title;
    @Column(name = "isbn_13")
    private String isbn13;
    @Column(name = "isbn_10")
    private String isbn10;
    private String author;
    @Column(name = "year_of_publication")
    private Integer yearOfPublication;
    @Column(name = "nb_of_pages")
    private Integer nbOfPages;
    @Min(1) @Max(10)
    private Integer rank;
    private BigDecimal price;
    @Column(name = "small_image_url")
    private URL smallImageUrl;
    @Column(name = "medium_image_url")
    private URL mediumImageUrl;
    @Column(length = 10000)
    @Size(min = 1, max = 10000)
    private String description;

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
    public String getAuthor() {
        return author;
    }
    public Integer getYearOfPublication() {
        return yearOfPublication;
    }
    public Integer getNbOfPages() {
        return nbOfPages;
    }
    public @Min(1) @Max(10) Integer getRank() {
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
    public @Size(min = 1, max = 10000) String getDescription() {
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
    public void setRank(@Min(1) @Max(10) Integer rank) {
        this.rank = rank;
    }
    public void setMediumImageUrl(URL mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }
    public void setDescription(@Size(min = 1, max = 10000) String description) {
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
    public void setTitle(@NotNull String title) {
        this.title = title;
    }
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
