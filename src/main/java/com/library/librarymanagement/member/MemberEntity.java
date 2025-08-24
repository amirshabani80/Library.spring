package com.library.librarymanagement.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "members")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate birthday;
    private Long phoneNumber;
    private String address;
}
