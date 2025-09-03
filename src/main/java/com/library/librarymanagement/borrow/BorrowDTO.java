package com.library.librarymanagement.borrow;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDTO {
    @NotEmpty(message = "enter a number ID")
    private Integer borrowId;
    @NotEmpty(message = "enter a number ID")
    private Integer bookId;
    @NotEmpty(message = "enter a number ID")
    private Integer memberId;
    @NotNull(message = "enter a date")
    private LocalDate borrowDate;
    @NotNull(message = "enter a date")
    private LocalDate returnDate;
}
