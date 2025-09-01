
package com.library.librarymanagement.borrow;

import com.library.librarymanagement.book.*;
import com.library.librarymanagement.member.*;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface BorrowMapper {

    BorrowDTO toDto(BorrowEntity borrowEntity);

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


/*@Mapper(componentModel = "spring")
public interface BorrowMapper {

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "member.id", target = "memberId")
    BorrowDTO toDto(BorrowEntity entity);

    @Mapping(source = "bookId", target = "book")
    @Mapping(source = "memberId", target = "member")
    BorrowEntity toEntity(BorrowDTO dto);

    default BookEntity mapBook(Integer id) {
        if (id == null) return null;
        BookEntity book = new BookEntity();
        book.setId(id);
        return book;
    }

    default MemberEntity mapMember(Integer id) {
        if (id == null) return null;
        MemberEntity member = new MemberEntity();
        member.setId(id);
        return member;
    }

    default Integer mapBook(BookEntity book) {
        return (book != null) ? book.getId() : null;
    }

    default Integer mapMember(MemberEntity member) {
        return (member != null) ? member.getId() : null;
    }
}*/




  /*  @Mapper(componentModel = "spring")
    public interface BorrowMapper {

        @Mapping(target = "bookId", expression = "java(mapBook(borrowEntity))")
        @Mapping(target = "memberId", expression = "java(mapMember(borrowEntity))")
        BorrowDTO toDto(BorrowEntity borrowEntity);

        default Integer mapBook(BorrowEntity borrowEntity) {
            return borrowEntity.getBook() != null ? borrowEntity.getBook().getId() : null;
        }

        default Integer mapMember(BorrowEntity borrowEntity) {
            return borrowEntity.getMember() != null ? borrowEntity.getMember().getId() : null;
        }



    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "memberId", source = "member.id")
    BorrowDTO toDto(BorrowEntity entity);

    @Mapping(target = "book", source = "bookId")
    @Mapping(target = "member", source = "memberId")
    BorrowEntity toEntity(BorrowDTO borrowDTO);

    default BookEntity mapBook(Integer id) {
        if (id == null) {
            return null;
        }
        BookEntity book = new BookEntity();
        book.setId(id);
        return book;
    }

    default MemberEntity mapMember(Integer id) {
        if (id == null) {
            return null;
        }
        MemberEntity member = new MemberEntity();
        member.setId(id);
        return member;
    }

    default Integer mapBook(BookEntity book) {
        if (book != null) {
            return book.getId();
        } else {
            return null;
        }
    }

    default Integer mapMember(MemberEntity member) {
        if (member != null) {
            return member.getId();
        } else {
            return null;
        }

    }
}*/
