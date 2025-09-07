package com.library.librarymanagement.borrow;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrow_history")
public class BorrowHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer borrowId;
    private Integer bookId;
    private Integer memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;
}


