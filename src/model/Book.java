package model;

import java.util.Date;
import java.time.LocalDate;

public class Book {

    private Long id;
    private String title;
    private Double price;
    private LocalDate releaseDate;
    private String description;
    private String photoName;
    private Integer productInStock;
    private Author author;
    private Category category;

    public Book() {
        // TODO Auto-generated constructor stub
    }

    public Book(String title,Double price, LocalDate releaseDate, String description, String photoName, Integer productInStock) {

        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.description = description;
        this.photoName = photoName;
        this.productInStock = productInStock;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Integer getProductInStock() {
        return productInStock;
    }

    public void setProductInStock(Integer productInStock) {
        this.productInStock = productInStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", description='" + description + '\'' +
                ", photoName='" + photoName + '\'' +
                ", productInStock=" + productInStock +
                ", author=" + author +
                ", category=" + category +
                '}';
    }
}
