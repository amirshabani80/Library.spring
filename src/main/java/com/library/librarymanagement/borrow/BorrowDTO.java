package com.library.librarymanagement.borrow;

import lombok.*;

import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDTO {
    private Integer id;
    private Integer bookId;
    private Integer memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
