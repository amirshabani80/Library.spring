package com.library.librarymanagement.borrow;


import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public List<BorrowDTO> borrowDTOList() {
        return borrowService.findAll();
    }

    @GetMapping("/delayed")
    public ResponseEntity<List<BorrowDTO>> delayedBook() {
        List<BorrowDTO> delayedBooks=borrowService.showDelayedBooks();
        return ResponseEntity.ok(delayedBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowDTO> getBorrowById(@Valid @PathVariable Integer id) {
        BorrowDTO borrowDTO = borrowService.findById(id);
        return ResponseEntity.ok(borrowDTO);
    }
    @GetMapping("/history")
    private List<BorrowDTO> BorrowHistoryRepository(){
        return borrowService.findAllHistory();
    }
    @GetMapping("/history/book/{bookId}")
    public ResponseEntity<List<BorrowDTO>> bookHistory(@Valid @PathVariable Integer bookId){
        List<BorrowDTO> borrowHistories=borrowService.bookHistory(bookId);
        return ResponseEntity.ok(borrowHistories);
    }
    @GetMapping("/history/member/{memberId}")
    public ResponseEntity<List<BorrowDTO>> memberHistory(@Valid @PathVariable Integer memberId){
        List<BorrowDTO> borrowHistories=borrowService.memberHistory(memberId);
        return ResponseEntity.ok(borrowHistories);
    }

    @PostMapping
    public ResponseEntity<BorrowDTO> borrowBook(@Valid @RequestBody BorrowDTO borrowDTO) {
        BorrowDTO borrowed = borrowService.borrowBook(borrowDTO);
        return ResponseEntity.ok(borrowed);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowDTO> updateBorrow(@Valid @PathVariable Integer id, @Valid @RequestBody BorrowDTO borrowDTO) {
        BorrowDTO updated = borrowService.updateBorrowRecord(id, borrowDTO);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> returnBook(@Valid @PathVariable Integer id) {
        boolean returnBook = borrowService.returnBook(id);
        return ResponseEntity.ok().build();
    }

}
