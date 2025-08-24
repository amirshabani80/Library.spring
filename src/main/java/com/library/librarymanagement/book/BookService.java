package com.library.librarymanagement.book;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BookService {

    public final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<BookDTO> findAll() {
        List<BookDTO> books = new ArrayList<>();
        List<BookEntity> bookEntity = bookRepository.findAll();
        for (BookEntity bookEntity1 : bookEntity) {
            books.add(BookMapper.toDto(bookEntity1));
        }
        return books;
    }

    public BookDTO findById(Integer id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDto)
                .orElse(null);

    }

    public BookDTO addBook(BookDTO bookDTO) {
        BookEntity bookEntity = bookRepository.save(BookMapper.toEntity(bookDTO));
        return BookMapper.toDto(bookEntity);
    }

    public BookDTO updateBook(Integer id, BookDTO bookDTO) {
        Optional<BookEntity> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            BookEntity newBook = optional.get();
            newBook.setName(bookDTO.getName());
            newBook.setWriter(bookDTO.getWriter());
            newBook.setPrice(bookDTO.getPrice());
            newBook.setStatus(bookDTO.getStatus());
            bookRepository.save(newBook);
            return BookMapper.toDto(newBook);
        } else {
            return null;
        }
    }

    public Boolean deleteBook(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
