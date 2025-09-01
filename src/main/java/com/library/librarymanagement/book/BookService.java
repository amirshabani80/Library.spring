package com.library.librarymanagement.book;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public boolean existsById(Integer id) {
        return bookRepository.existsById(id);
    }
    public List<BookDTO> findAll() {
        List<BookDTO> books = new ArrayList<>();
        List<BookEntity> bookEntity = bookRepository.findAll();
        for (BookEntity bookEntity1 : bookEntity) {
            books.add(bookMapper.toDto(bookEntity1));
        }
        return books;
    }

    public BookDTO findById(Integer id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElse(null);

    }

    public BookDTO addBook(BookDTO bookDTO) {
        BookEntity bookEntity = bookRepository.save(bookMapper.toEntity(bookDTO));
        return bookMapper.toDto(bookEntity);
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
            return bookMapper.toDto(newBook);
        } else {
            return null;
        }
    }

    public BookDTO getOptional(Integer id) {
        Optional<BookEntity> optional = bookRepository.findById(id);
        return bookMapper.toDto(optional.get());
    }

    public BookDTO markAsBorrowed(Integer id) {
        BookDTO bookDTO=getOptional(id);
        bookDTO.setStatus(BookEntity.Status.BORROWED);
        bookRepository.save(bookMapper.toEntity(bookDTO));
        return bookDTO;
    }

    public BookDTO markAsAvailable(Integer id) {
        BookDTO bookDTO=getOptional(id);
        bookDTO.setStatus(BookEntity.Status.AVAILABLE);
        bookRepository.save(bookMapper.toEntity(bookDTO));
        return bookDTO;
    }

    public BookDTO markAsNeedrepaire(Integer id) {
        BookDTO bookDTO=getOptional(id);
        bookDTO.setStatus(BookEntity.Status.NEEDREPAIRE);
        bookRepository.save(bookMapper.toEntity(bookDTO));
        return bookDTO;
    }


    public Boolean deleteBook(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean isAvailable(Integer bookId) {
        BookDTO bookDTO = getOptional(bookId);

        if (bookDTO == null) {
            throw new NoSuchElementException("Book not found with ID: " + bookId);
        }

        System.out.println("Book ID: " + bookId + ", Status: " + bookDTO.getStatus());

        return BookEntity.Status.AVAILABLE.equals(bookDTO.getStatus());
    }


}
