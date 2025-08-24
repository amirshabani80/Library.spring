package com.library.librarymanagement.book;


import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @GetMapping
    public List<BookDTO> bookDTOS(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id){
        BookDTO book=bookService.findById(id);
        if (book!=null){
            return ResponseEntity.ok(book);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        BookDTO savedBook=bookService.addBook(bookDTO);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id,@RequestBody BookDTO bookDTO){
        BookDTO updatedBook=bookService.updateBook(id,bookDTO);
        if (updatedBook !=null){
            return ResponseEntity.ok(updatedBook);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteBook(@PathVariable Integer id){
        boolean deleted=bookService.deleteBook(id);
        if (deleted){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }



}
