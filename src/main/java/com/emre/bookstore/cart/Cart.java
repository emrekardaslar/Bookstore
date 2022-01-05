package com.emre.bookstore.cart;

import com.emre.bookstore.book.Book;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "jsonb")
    private Book[] books;
    private BigDecimal total;
    private Long customerId;

    public Cart() {
    }

    public Cart(Long id, Book[] books, BigDecimal total) {
        this.id = id;
        this.books = books;
        this.total = total;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
