package com.library.librarymanagement.book;

public class BookMapper {

    public static BookEntity toEntity(BookDTO bookDTO) {
        return new BookEntity(
                bookDTO.getId(),
                bookDTO.getName(),
                bookDTO.getWriter(),
                bookDTO.getPrice(),
                bookDTO.getStatus());
    }
    public static BookDTO toDto(BookEntity bookEntity){
        return new BookDTO(
                bookEntity.getId(),
                bookEntity.getName(),
                bookEntity.getWriter(),
                bookEntity.getPrice(),
                bookEntity.getStatus());
    }
}
