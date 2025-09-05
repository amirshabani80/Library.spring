package com.library.librarymanagement.book;


import org.mapstruct.*;

@Mapper(componentModel = "spring",uses ={})
public interface BookMapper{
    BookDTO toDto(BookEntity bookEntity);

    BookEntity toEntity(BookDTO bookDTO);
}