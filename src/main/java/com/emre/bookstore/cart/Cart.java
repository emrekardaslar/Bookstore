package com.emre.bookstore.cart;

import com.emre.bookstore.book.Book;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Book[] books;
    private double total;
    @Column(name = "customer_id", nullable = false, unique = true)
    private Long customerId;

    public Cart() {
    }

    public Cart(Long id, Book[] books, double total, Long customerId) {
        this.id = id;
        this.books = books;
        this.total = total;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
