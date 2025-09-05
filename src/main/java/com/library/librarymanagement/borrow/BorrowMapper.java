
package com.library.librarymanagement.borrow;

import com.library.librarymanagement.book.*;
import com.library.librarymanagement.member.*;
import org.mapstruct.*;


@Mapper(componentModel = "spring",uses ={})
public interface BorrowMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "member.id", target = "memberId")
    BorrowDTO toDto(BorrowEntity borrowEntity);
    @Mapping(source = "bookId", target = "book")
    @Mapping(source = "memberId", target = "member")
    BorrowEntity toEntity(BorrowDTO borrowDTO);

    default Integer map(BookEntity book) {
        return (book != null) ? book.getId() : null;
    }


    default BookEntity mapBook(Integer bookId) {
        if (bookId == null) return null;
        BookEntity book = new BookEntity();
        book.setId(bookId);
        return book;
    }

    default Integer map(MemberEntity member) {
        return (member != null) ? member.getId() : null;
    }

    default MemberEntity mapMember(Integer memberId) {
        if (memberId == null) return null;
        MemberEntity member = new MemberEntity();
        member.setId(memberId);
        return member;
    }
}
