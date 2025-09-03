package com.library.librarymanagement.book;


import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> bookDTOS() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@Valid @PathVariable Integer id) {
        BookDTO book = bookService.findById(id);
            return ResponseEntity.ok(book);
        }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO savedBook = bookService.addBook(bookDTO);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@Valid @PathVariable Integer id,@Valid @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
            return ResponseEntity.ok(updatedBook);
        }
    @PatchMapping("/{id}")
public ResponseEntity<BookDTO> updateStatus(@Valid @PathVariable Integer id,@Valid @RequestBody String status) {
        BookDTO updateStatus = bookService.changeStatus(id, status);
        return ResponseEntity.ok(updateStatus);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@Valid @PathVariable Integer id) {
        boolean deleted = bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }


}
