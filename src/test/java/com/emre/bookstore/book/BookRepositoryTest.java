package com.emre.bookstore.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findBookByName() {
        String name = "Book Test";
        Book book = new Book();
        book.setName(name);
        book.setAuthor("author");

        bookRepository.save(book);
        Optional<Book> searchBook = bookRepository.findBookByName(name);
        boolean exists = searchBook.isPresent();
        assertThat(exists).isTrue();
    }

    @Test
    void findBookByNameNotExists() {
        String name = "Book Test";
        Book book = new Book();
        book.setName("X");
        book.setAuthor("author");

        bookRepository.save(book);
        Optional<Book> searchBook = bookRepository.findBookByName(name);
        boolean exists = searchBook.isPresent();
        assertThat(exists).isFalse();
    }

    @Test
    void findBookById() {
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

}