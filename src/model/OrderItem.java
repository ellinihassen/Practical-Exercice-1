package model;

import model.Book;

public class OrderItem {

    private Long id;
    private Book book;
    private Integer quantity;
    private Order order;


    public OrderItem() {
        // TODO Auto-generated constructor stub
    }

    public OrderItem(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;

    }

    public Double calaculateTotalPrice() {
        return book.getPrice() * quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Item :" + book + ", quantity=" + quantity + " \n";
    }



}
