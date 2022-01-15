package com.emre.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping(path = "{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable("bookId") Long bookId){
        return new ResponseEntity<Book>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.OK);
    }

    @DeleteMapping(path = "{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") Long bookId) {
        try {
            return new ResponseEntity<Book>(bookService.deleteBook(bookId), HttpStatus.OK);
        }

        catch (Exception e) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("status", "400");
            body.put("message", "No book to delete with " + bookId);
            return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book newBook) {
        return new ResponseEntity<>(bookService.updateBook(bookId, newBook), HttpStatus.OK);
    }

}
