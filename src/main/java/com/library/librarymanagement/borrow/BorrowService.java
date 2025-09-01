package com.library.librarymanagement.borrow;


import com.library.librarymanagement.book.*;
import com.library.librarymanagement.member.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMapper borrowMapper;
    private final BookService bookService;
    private final MemberService memberService;

    public BorrowService(BorrowRepository borrowRepository, BorrowMapper borrowMapper,
                         BookService bookService, MemberService memberService) {
        this.borrowRepository = borrowRepository;
        this.borrowMapper = borrowMapper;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public List<BorrowDTO> findAll() {
        List<BorrowDTO> borrowedBooks = new ArrayList<>();
        List<BorrowEntity> borrowEntities = borrowRepository.findAll();
        for (BorrowEntity entity : borrowEntities
        ) {
            borrowedBooks.add(borrowMapper.toDto(entity));
        }
        return borrowedBooks;
    }

    public BorrowDTO findById(Integer id) {
        return borrowRepository.findById(id)
                .map(borrowMapper::toDto)
                .orElse(null);
    }


    public BorrowDTO borrowBook(BorrowDTO borrowDTO) {
        Integer bookId = borrowDTO.getBookId();
        Integer memberId = borrowDTO.getMemberId();

        if (!memberService.existsById(memberId)) {
            throw new NoSuchElementException("Member not found with ID: " + memberId);
        }

        if (!bookService.existsById(bookId)) {
            throw new NoSuchElementException("Book not found with ID: " + bookId);
        }

        if (!bookService.isAvailable(bookId)) {

            throw new IllegalStateException("Book is not AVAILABLE");
        }

        bookService.markAsBorrowed(bookId);

        BorrowEntity borrowEntity=borrowRepository.save(borrowMapper.toEntity(borrowDTO));
        return borrowMapper.toDto(borrowEntity);
    }
}
        /*BookDTO bookDTO = bookService.findById(borrowDTO.getBookId());
        MemberDTO memberDTO = memberService.findById(borrowDTO.getMemberId());
        if (memberDTO == null || bookDTO == null) {
            throw new IllegalArgumentException("Book ID and Member ID must not be null");
        } else if (!bookService.existsById(bookDTO.getId())) {
            throw new IllegalArgumentException("Invalid Book!!!");
        } else if (bookService.checkStatus(bookDTO.getId())) {
            BorrowEntity entity = borrowRepository.save(borrowMapper.toEntity(borrowDTO));
            bookService.markAsBorrowed(bookDTO.getId());
            return borrowMapper.toDto(entity);
        }
*/



