package com.library.librarymanagement.member;

import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private Long phoneNumber;
    private String address;
}
