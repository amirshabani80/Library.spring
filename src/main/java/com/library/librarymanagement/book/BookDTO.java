package com.library.librarymanagement.book;

import jakarta.validation.constraints.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    @NotEmpty(message = "please enter a name")
    @Size(min = 2)
    private String name;
    @NotEmpty(message = "please enter a writer name")
    @Size(min = 2)
    private String writer;
    @NotNull(message = "please enter price")
    private Integer price;
    private BookEntity.Status status;
}

