package com.library.librarymanagement.borrow;

import com.library.librarymanagement.book.*;
import com.library.librarymanagement.member.*;

public class BorrowMapper {

    public static BorrowDTO toDto(BorrowEntity entity) {
        return new BorrowDTO(
                entity.getId(),
                entity.getBook().getId(),
                entity.getMember().getId(),
                entity.getBorrowDate(),
                entity.getReturnDate());
    }

    public static BorrowEntity toEntity(BorrowDTO borrowDTO, BookEntity bookEntity, MemberEntity memberEntity) {
        return new BorrowEntity(
                borrowDTO.getId(),
                bookEntity,
                memberEntity,
                borrowDTO.getBorrowDate(),
                borrowDTO.getReturnDate()
        );
    }
}
