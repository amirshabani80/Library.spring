package com.library.librarymanagement.member;


import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberEntity toEntity(MemberDTO memberDTO);
    MemberDTO toDto(MemberEntity memberEntity);
}
