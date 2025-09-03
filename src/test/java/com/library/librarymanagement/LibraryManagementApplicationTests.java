package com.library.librarymanagement;

import com.library.librarymanagement.member.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.time.*;

@SpringBootTest
    class MemberMapperTest {


    @Autowired
    private MemberMapper memberMapper;


    @Test
    void testMapper() {
        MemberEntity entity = new MemberEntity(1, "Ali", LocalDate.now(), 9123456789L, "Tehran");
        MemberDTO dto = memberMapper.toDto(entity);
        System.out.println(dto);
    }
}



