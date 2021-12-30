package com.emre.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }


    public Book addBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findBookByName(book.getName());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("book is already in db");
        }

        this.bookRepository.save(book);
        return book;
    }

    public Book deleteBook(Long id) {
        boolean exists = bookRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("book with id " + " does not exists");
        }
        Book bookToDelete = bookRepository.findBookById(id);
        bookRepository.deleteById(id);
        return bookToDelete;
    }

    public Book updateBook(Long id, Book newBook) {
        boolean exists = bookRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("book with id " + " does not exists");
        }

        bookRepository.save(newBook);
        return bookRepository.findBookById(id);
    }
    //TODO add validations
    //TODO add tests
    //TODO dockerize springboot, angular and postgresql
}
