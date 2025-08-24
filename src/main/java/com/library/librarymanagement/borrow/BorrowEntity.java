package com.library.librarymanagement.borrow;

import com.library.librarymanagement.book.*;
import com.library.librarymanagement.member.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrowed_books")
public class BorrowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
   private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private MemberEntity member;

    private LocalDate borrowDate;
    private LocalDate returnDate;
}
