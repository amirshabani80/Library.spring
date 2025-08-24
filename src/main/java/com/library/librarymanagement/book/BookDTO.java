package com.library.librarymanagement.book;
import lombok.*;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class BookDTO {
        private Integer id;
        private String name;
        private String writer;
        private Integer price;
        private BookEntity.Status status;
    }

