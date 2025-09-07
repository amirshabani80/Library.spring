package com.library.librarymanagement.borrow;


import com.library.librarymanagement.book.*;
import com.library.librarymanagement.exception.*;
import com.library.librarymanagement.member.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMapper borrowMapper;
    private final BookService bookService;
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final BookMapper bookMapper;
    private final BorrowHistoryRepository borrowHistoryRepository;

    public BorrowService(BorrowRepository borrowRepository, BorrowMapper borrowMapper,
                         BookService bookService, MemberService memberService,
                         MemberMapper memberMapper, BookMapper bookMapper, BorrowHistoryRepository borrowHistoryRepository) {
        this.borrowRepository = borrowRepository;
        this.borrowMapper = borrowMapper;
        this.bookService = bookService;
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.bookMapper = bookMapper;
        this.borrowHistoryRepository = borrowHistoryRepository;
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
                .orElseThrow(() -> new NotFoundException("Borrow record with id " + id + " not found"));
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
        MemberEntity member = memberMapper.toEntity(memberService.findById(memberId));
        BookEntity book = bookMapper.toEntity(bookService.findById(bookId));
        BorrowEntity borrowEntity = new BorrowEntity();
        borrowEntity.setMember(member);
        borrowEntity.setBook(book);
        borrowEntity.setBorrowDate(borrowDTO.getBorrowDate());
        borrowEntity.setReturnDate(borrowDTO.getReturnDate());
        borrowRepository.save(borrowEntity);
        BorrowHistoryEntity borrowHistory = new BorrowHistoryEntity();
        borrowHistory.setBorrowId(borrowEntity.getBorrowId());
        borrowHistory.setBookId(borrowEntity.getBookID());
        borrowHistory.setMemberId(borrowEntity.getMemberId());
        borrowHistory.setBorrowDate(borrowEntity.getBorrowDate());
        borrowHistory.setReturnDate(borrowEntity.getReturnDate());
        borrowHistory.setStatus("BORROWED");
        borrowHistoryRepository.save(borrowHistory);
        bookService.markAsBorrowed(bookId);
        return borrowMapper.toDto(borrowEntity);
    }

    public List<BorrowDTO> showDelayedBooks() {
        List<BorrowDTO> delayedBook = new ArrayList<>();
        List<BorrowEntity> borrowedBooks = borrowRepository.findAll();
        for (BorrowEntity book : borrowedBooks) {
            long daysLate = calculateDelayDays(book.getReturnDate());
            if (daysLate > 0) {
                delayedBook.add(borrowMapper.toDto(book));
            }
        }
        return delayedBook;
    }

    private long calculateDelayDays(LocalDate returnDate) {
        LocalDate today = LocalDate.now();

        if (today.isAfter(returnDate)) {
            return ChronoUnit.DAYS.between(returnDate, today);
        } else {
            return 0;
        }
    }


    public BorrowDTO updateBorrowRecord(Integer id, BorrowDTO borrowDTO) {
        Optional<BorrowEntity> optional = borrowRepository.findById(id);
        MemberEntity member = memberMapper.toEntity(memberService.findById(borrowDTO.getMemberId()));
        BookEntity book = bookMapper.toEntity(bookService.findById(borrowDTO.getBookId()));
        if (optional.isPresent()) {
            BorrowEntity entity = optional.get();
            entity.setBorrowDate(borrowDTO.getBorrowDate());
            entity.setReturnDate(borrowDTO.getReturnDate());
            entity.setMember(member);
            entity.setBook(book);
            borrowRepository.save(entity);
            BorrowHistoryEntity borrowHistory = new BorrowHistoryEntity();
            borrowHistory.setBorrowId(entity.getBorrowId());
            borrowHistory.setBookId(entity.getBookID());
            borrowHistory.setMemberId(entity.getMemberId());
            borrowHistory.setBorrowDate(entity.getBorrowDate());
            borrowHistory.setReturnDate(entity.getReturnDate());
            borrowHistory.setStatus("BORROWED");
            borrowHistoryRepository.save(borrowHistory);
            return borrowMapper.toDto(entity);
        } else
            throw new NotFoundException("Borrow record with id " + id + " not found");
    }

    public boolean returnBook(Integer borrowId) {
        if (borrowRepository.existsById(borrowId)) {
            Optional<BorrowEntity> entity = borrowRepository.findById(borrowId);
            BorrowEntity borrowEntity = entity.get();
            bookService.markAsAvailable(borrowEntity.getBookID());
            borrowRepository.deleteById(borrowId);
            BorrowHistoryEntity borrowHistory=borrowHistoryRepository.findByBorrowId(borrowId);
            borrowHistory.setStatus("AVAILABLE");
            borrowHistoryRepository.save(borrowHistory);
            return true;
        } else
            throw new NotFoundException("Borrow record with id " + borrowId + " not found");
    }


    public List<BorrowDTO>findAllHistory() {
        List<BorrowHistoryEntity> borrowEntities = borrowHistoryRepository.findAll();
return borrowMapper.toDtoList(borrowEntities);
    }

    public List<BorrowDTO> bookHistory(Integer id) {
         List<BorrowHistoryEntity> borrowEntities=borrowHistoryRepository.findByBookId(id);
        return borrowMapper.toDtoList(borrowEntities);
    }

    public List<BorrowDTO> memberHistory(Integer id) {
        return borrowMapper.toDtoList(borrowHistoryRepository.findByMemberId(id));
    }
}



