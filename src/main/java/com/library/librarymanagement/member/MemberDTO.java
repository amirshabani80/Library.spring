package com.library.librarymanagement.member;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    @NotNull(message = "enter a number ID")
    private Integer id;
    @NotNull(message = "please enter a name")
    private String name;
    @NotNull(message = "please enter a date")
    private LocalDate birthday;
    @NotNull(message = "please enter a phone number")
    private Long phoneNumber;
    @NotNull(message = "please enter a address")
    private String address;
}
