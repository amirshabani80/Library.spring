package com.library.librarymanagement.borrow;


import com.library.librarymanagement.book.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<BorrowDTO> getBorrowById(@PathVariable Integer id) {
        BorrowDTO borrowDTO = borrowService.findById(id);
        if (borrowDTO != null) {
            return ResponseEntity.ok(borrowDTO);
        } else
            return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<BorrowDTO> borrowBook(@RequestBody BorrowDTO borrowDTO){
       BorrowDTO borrowed=borrowService.borrowBook(borrowDTO);
        return ResponseEntity.ok(borrowed);
    }

}
